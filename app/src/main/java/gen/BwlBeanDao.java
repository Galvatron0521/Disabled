package gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;


import com.shenkangyun.disabledproject.BeanFolder.BwlBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "BWL_BEAN".
*/
public class BwlBeanDao extends AbstractDao<BwlBean, Long> {

    public static final String TABLENAME = "BWL_BEAN";

    /**
     * Properties of entity BwlBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property IsTX = new Property(3, boolean.class, "isTX", false, "IS_TX");
        public final static Property Day = new Property(4, int.class, "day", false, "DAY");
        public final static Property Mouth = new Property(5, int.class, "mouth", false, "MOUTH");
        public final static Property Year = new Property(6, int.class, "year", false, "YEAR");
        public final static Property Time = new Property(7, String.class, "time", false, "TIME");
        public final static Property Start = new Property(8, String.class, "Start", false, "START");
        public final static Property End = new Property(9, String.class, "End", false, "END");
    }


    public BwlBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BwlBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BWL_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"TITLE\" TEXT," + // 1: title
                "\"CONTENT\" TEXT," + // 2: content
                "\"IS_TX\" INTEGER NOT NULL ," + // 3: isTX
                "\"DAY\" INTEGER NOT NULL ," + // 4: day
                "\"MOUTH\" INTEGER NOT NULL ," + // 5: mouth
                "\"YEAR\" INTEGER NOT NULL ," + // 6: year
                "\"TIME\" TEXT," + // 7: time
                "\"START\" TEXT," + // 8: Start
                "\"END\" TEXT);"); // 9: End
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BWL_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BwlBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
        stmt.bindLong(4, entity.getIsTX() ? 1L: 0L);
        stmt.bindLong(5, entity.getDay());
        stmt.bindLong(6, entity.getMouth());
        stmt.bindLong(7, entity.getYear());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String Start = entity.getStart();
        if (Start != null) {
            stmt.bindString(9, Start);
        }
 
        String End = entity.getEnd();
        if (End != null) {
            stmt.bindString(10, End);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BwlBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
        stmt.bindLong(4, entity.getIsTX() ? 1L: 0L);
        stmt.bindLong(5, entity.getDay());
        stmt.bindLong(6, entity.getMouth());
        stmt.bindLong(7, entity.getYear());
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String Start = entity.getStart();
        if (Start != null) {
            stmt.bindString(9, Start);
        }
 
        String End = entity.getEnd();
        if (End != null) {
            stmt.bindString(10, End);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BwlBean readEntity(Cursor cursor, int offset) {
        BwlBean entity = new BwlBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.getShort(offset + 3) != 0, // isTX
            cursor.getInt(offset + 4), // day
            cursor.getInt(offset + 5), // mouth
            cursor.getInt(offset + 6), // year
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Start
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // End
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BwlBean entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIsTX(cursor.getShort(offset + 3) != 0);
        entity.setDay(cursor.getInt(offset + 4));
        entity.setMouth(cursor.getInt(offset + 5));
        entity.setYear(cursor.getInt(offset + 6));
        entity.setTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStart(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setEnd(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BwlBean entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BwlBean entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BwlBean entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}