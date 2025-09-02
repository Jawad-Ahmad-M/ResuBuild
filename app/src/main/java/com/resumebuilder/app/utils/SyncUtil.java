package com.resumebuilder.app.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncUtil {

    /**
     * Extracts the unique ID from an entity.
     */
    public interface IdExtractor<T> {
        int getId(T item);
    }

    /**
     * Compares two entities of the same type.
     * Should return true if they are equal (no need to update).
     */
    public interface ItemComparator<T> {
        boolean areEqual(T oldItem, T newItem);
    }

    /**
     * Generic interface for performing DAO operations.
     */
    public interface DaoOps<T> {
        void insert(T item);
        void update(T item);
        void delete(T item);
    }

    /**
     * Syncs UI list with DB list: insert new items, update changed ones, delete removed ones.
     *
     * @param dbList       Data currently in DB
     * @param uiList       Data currently shown in UI
     * @param idExtractor  Provides unique ID of an item
     * @param comparator   Compares two items for equality
     * @param daoOps       DAO operations (insert/update/delete)
     * @param <T>          Entity type
     */
    public static <T> void sync(
            List<T> dbList,
            List<T> uiList,
            IdExtractor<T> idExtractor,
            ItemComparator<T> comparator,
            DaoOps<T> daoOps
    ) {
        Map<Integer, T> dbMap = new HashMap<>();
        for (T dbItem : dbList) {
            dbMap.put(idExtractor.getId(dbItem), dbItem);
        }

        Map<Integer, T> uiMap = new HashMap<>();
        for (T uiItem : uiList) {
            int id = idExtractor.getId(uiItem);
            if (id != 0) { // 0 means it's new and should be inserted
                uiMap.put(id, uiItem);
            }
        }

        // Handle update and delete
        for (T dbItem : dbList) {
            int id = idExtractor.getId(dbItem);
            T uiItem = uiMap.get(id);

            if (uiItem == null) {
                // Deleted from UI
                daoOps.delete(dbItem);
            } else if (!comparator.areEqual(dbItem, uiItem)) {
                // Updated in UI
                daoOps.update(uiItem);
            }
        }

        // Handle insert
        for (T uiItem : uiList) {
            if (idExtractor.getId(uiItem) == 0) {
                daoOps.insert(uiItem);
            }
        }
    }
}
