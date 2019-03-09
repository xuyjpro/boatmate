package com.xuyj.boatmate.tools;

import java.util.List;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public final class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    // 线程局部模式
    private static ThreadLocal<Session> threadLoacal = new ThreadLocal<Session>();
    private HibernateUtil() {}
    static {
    	
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    // 获取全新的session
    public static Session openSession() {
        return sessionFactory.openSession();
    }
    // 获取和线程关联的session
    public static Session getCurrentSession() {
        Session session = threadLoacal.get();
        // 判断是否得到
        if(session==null) {
            session = sessionFactory.openSession();
            // 将session放入threadLocal
            threadLoacal.set(session);
        }
        return session;
    }
    public static void closeCurrentSession() {
        Session session = getCurrentSession();
        if(session!=null && session.isOpen()) {
            session.close();
            threadLoacal.set(null);
        }
    }

    // 根据id返回对象的方法
    public static Object findById(Class clazz, java.io.Serializable id) {
        Session session = null;
        Transaction ts = null;
        Object obj = null;
        try {
            session = openSession();
            ts = session.beginTransaction();

            obj = session.load(clazz, id);

            ts.commit();
        } catch (Exception e) {
            if(ts!=null) {
                ts.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }
        return obj;
    }

    // 根据id返回对象的方法
    public static Object findByIdOpenInView(Class clazz, java.io.Serializable id) {
        Session session = getCurrentSession();
        Object obj = session.load(clazz, id);
        return obj;
    }
    // 返回至多一个对象
    public static Object uniqueQuery(String hql, String[] paras) {
        Session session = null;
        Object obj = null;
        try {
            session = openSession();

            Query query = session.createQuery(hql);

            if(paras!=null && paras.length>0) {
                for(int i=0; i<paras.length; i++) {
                    query.setString(i, paras[i]);
                }
            }
            obj = query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }

        return obj;
    }
    /// 分页
    public static List executeQueryByPage(String hql, String[] paras, int pageSize, int pageNow) {
        Session session = null;
        List list = null;
        try {
            session = openSession();

            Query query = session.createQuery(hql);

            if(paras!=null && paras.length>0) {
                for(int i=0; i<paras.length; i++) {
                    query.setString(i, paras[i]);
                }
            }

            /// 
            query.setFirstResult((pageNow-1)*pageSize).setMaxResults(pageSize);

            list = query.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }

        return list;
    }

    /// 分页
    public static List executeQueryByPageOpenInView(String hql, String[] paras, int pageSize, int pageNow) {
        Session session = getCurrentSession();

        Query query = session.createQuery(hql);

        if(paras!=null && paras.length>0) {
            for(int i=0; i<paras.length; i++) {
                query.setString(i, paras[i]);
            }
        }

        /// 
        query.setFirstResult((pageNow-1)*pageSize).setMaxResults(pageSize);

        List list = query.list();

        return list;
    }

    /// 查询接口
    public static List executeQuery(String hql, String[] paras) {
        Session session = null;
        List list = null;
        try {
            session = openSession();

            Query query = session.createQuery(hql);

            if(paras!=null && paras.length>0) {
                for(int i=0; i<paras.length; i++) {
                    query.setString(i, paras[i]);
                }
            }
            list = query.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }

        return list;
    }

    /// 查询接口
    public static List executeQueryOpenInView(String hql, String[] paras) {
        Session session = getCurrentSession();

        Query query = session.createQuery(hql);

        if(paras!=null && paras.length>0) {
            for(int i=0; i<paras.length; i++) {
                query.setString(i, paras[i]);
            }
        }
        List list = query.list();

        return list;
    }
    /// 修改和删除 批量sql
    public static void executeUpdate(String hql, String[] paras) {
        Session session = null;
        Transaction ts = null;
        try {
            session = openSession();
            ts = session.beginTransaction();
            Query query = session.createQuery(hql);

            if(paras!=null && paras.length>0) {
                for(int i=0; i<paras.length; i++) {
                    query.setString(i, paras[i]);
                }
            }

            query.executeUpdate();

            ts.commit();
        } catch (Exception e) {
            if(ts!=null) {
                ts.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }
    }

    /// 修改和删除 批量sql
    public static void executeUpdateOpenInView(String hql, String[] paras) {
        Session session = getCurrentSession();

        Query query = session.createQuery(hql);

        if(paras!=null && paras.length>0) {
            for(int i=0; i<paras.length; i++) {
                query.setString(i, paras[i]);
            }
        }

        query.executeUpdate();
    }

    // 添加
    public static void save(Object obj) {
        Session session = null;
        Transaction ts = null;
        try {
            session = openSession();
            ts = session.beginTransaction();
            session.save(obj);
            ts.commit();
        } catch (Exception e) {
            if(ts!=null) {
                ts.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(session!=null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 添加
    public static void saveOpenInView(Object obj) {
        Session session = getCurrentSession();
        session.save(obj);
    }
}
