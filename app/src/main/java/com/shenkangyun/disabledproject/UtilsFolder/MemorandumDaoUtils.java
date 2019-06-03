package com.shenkangyun.disabledproject.UtilsFolder;

import android.content.Context;


import com.ldf.calendar.model.CalendarDate;
import com.shenkangyun.disabledproject.BaseFolder.DaoManager;
import com.shenkangyun.disabledproject.BeanFolder.BwlBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import gen.BwlBeanDao;

/**
 * Created by 16001 on 2017/10/19 0019.
 */

public class MemorandumDaoUtils {

    private DaoManager mManager;

    public MemorandumDaoUtils(Context context) {
        this.mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成bean记录的插入，如果表未创建，先创建Meizi表
     *
     * @param bean
     * @return
     */

    public boolean insertMemorandum(BwlBean bean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getBwlBeanDao().insert(bean) == -1 ? false : true;
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @param beanList
     * @return
     */
    public boolean insertMultMemorandum(final List<BwlBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (BwlBean bean : beanList) {
                        mManager.getDaoSession().insertOrReplace(bean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param bean
     * @return
     */
    public boolean updateMemorandum(BwlBean bean) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param bean
     * @return
     */
    public boolean deleteMemorandum(BwlBean bean) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(BwlBean.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<BwlBean> queryAllRecord() {
        return mManager.getDaoSession().loadAll(BwlBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public BwlBean queryRecordById(long key) {
        return mManager.getDaoSession().load(BwlBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<BwlBean> queryRecordByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(BwlBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     *
     * @return
     */
    public List<BwlBean> queryMemorandumByQueryBuilder(long id) {
        QueryBuilder<BwlBean> queryBuilder = mManager.getDaoSession().queryBuilder(BwlBean.class);
        return queryBuilder.where(BwlBeanDao.Properties._id.eq(id)).list();
    }

    //同一日的备忘
    public List<BwlBean> queryMemorandumByDate(CalendarDate date) {
        QueryBuilder<BwlBean> queryBuilder = mManager.getDaoSession().queryBuilder(BwlBean.class);
        return queryBuilder.where(BwlBeanDao.Properties.Day.eq(date.getDay()), BwlBeanDao.Properties.Mouth.eq(date.getMonth()), BwlBeanDao.Properties.Year.eq(date.getYear())).list();
    }


}
