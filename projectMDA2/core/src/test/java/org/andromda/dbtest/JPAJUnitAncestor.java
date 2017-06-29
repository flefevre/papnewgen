// license-header java merge-point
//
/**
 * Generated by test/JPAJUnitAncestor.java.vsl in andromda-ejb3-cartridge on 06/29/2017 15:09:15.
 * This file can be safely modified. If deleted it will be regenerated.
 */
package org.andromda.dbtest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * Contains methods used by multiple JPA unit tests, to initialize and log
 */
public abstract class JPAJUnitAncestor
{
    private static Logger LOGGER = LogManager.getLogger(JPAJUnitAncestor.class);
    /** EntityManagerFactory used here and in descendants - threadsafe */
    public static EntityManagerFactory emf;
    /** EntityManager - not threadsafe */
    protected EntityManager em;
    /** "UNIT_TEST" */
    public static final String PERSISTENCE_UNIT = "UNIT_TEST";
    /** Entity to log data on, and delete from */
    protected String entityName = null;
    /** Table to log data on, and delete from */
    protected String table = null;

    /**
     *
     */
    public JPAJUnitAncestor()
    {
        // Public no arg constructor
    }

    /**
     * @param entityNameIn Entity Name for criteria queries
     */
    public JPAJUnitAncestor(final String entityNameIn)
    {
        this.entityName = entityNameIn;
    }

    /**
     * Creates EntityManagerFactory and EntityManager
     * Run before class is tested
     */
    public void setUpBeforeClass()
    {
        // Test class specific setUpBeforeClass
        JPAJUnitAncestor.LOGGER.debug("creating entity manager");
        JPAJUnitAncestor.emf = Persistence.createEntityManagerFactory(JPAJUnitAncestor.PERSISTENCE_UNIT);
        Assert.assertNotNull(JPAJUnitAncestor.emf);
        this.em = JPAJUnitAncestor.emf.createEntityManager();
        Assert.assertNotNull(this.getEm());
    }

    /**
     * Runs before each test
     */
    public void setUp()
    {
        // Test class specific before each test
        //this.em = JPAJUnitAncestor.emf.createEntityManager();
        Assert.assertNotNull(this.getEm());
        this.getEm().getTransaction().begin();
    }

    /**
     * Runs after class test
     */
    public void tearDownAfterClass()
    {
        // Test specific tearDownAfterClass
    }

    /**
     * Flush all pending transactions. Run logEntities from descendant to log the current rowcount.
     * Run deleteAll from descendant to delete all added rows.
     */
    public void tearDown()
    {
        //JPAJUnitAncestor.LOGGER.debug("tearDownAfterClass() started, em=" + this.em);
        Assert.assertNotNull(this.getEm());
        EntityTransaction trans = this.getEm().getTransaction();
        if (trans.isActive())
        {
            if (trans.getRollbackOnly())
            {
                trans.rollback();
                //JPAJUnitAncestor.LOGGER.info("tearDown rollback Transaction");
            }
            else
            {
                trans.commit();
                //JPAJUnitAncestor.LOGGER.info("tearDown commit Transaction");
            }
        }
        else
        {
            JPAJUnitAncestor.LOGGER.warn("tearDown Transaction not active");
        }
        // Run closeAll() after logging test output from DB query
    }

    /**
     * To be run after tearDownAfterClass
     */
    public void closeAll()
    {
        EntityManager emClose = this.getEm();
        if (emClose != null)
        {
            try
            {
                EntityTransaction trans = emClose.getTransaction();
                if (trans.isActive())
                {
                    if (trans.getRollbackOnly())
                    {
                        trans.rollback();
                        //JPAJUnitAncestor.LOGGER.info("closeAll rollback Transaction");
                    }
                    else
                    {
                        trans.commit();
                        //JPAJUnitAncestor.LOGGER.info("closeAll commit Transaction");
                    }
                }
            }
            finally
            {
                emClose.close();
                if (JPAJUnitAncestor.emf != null)
                {
                    JPAJUnitAncestor.emf.close();
                }
                JPAJUnitAncestor.LOGGER.debug("closeAll() complete, em=" + emClose);
            }
        }
    }

    /**
     * Log number of rows in table
     * @return number of rows in table
     */
    public int logEntities()
    {
        if (this.entityName != null)
        {
            final Query query = this.getEm().createQuery("select a from " + this.entityName + " as a");
            @SuppressWarnings("rawtypes")
            List results = query.getResultList();
            //JPAJUnitAncestor.LOGGER.info("select a from " + this.entityName + " as a");
            for (final Object entity : results)
            {
                JPAJUnitAncestor.LOGGER.info(entity);
            }
            return results.size();
        }
        return 0;
    }

    /**
     * Delete all rows in table
     */
    public void deleteAll()
    {
        if (this.entityName != null)
        {
            final Query query = this.getEm().createNativeQuery("delete from " + this.table);
            final int rows = query.executeUpdate();
            JPAJUnitAncestor.LOGGER.info("removed " + rows + " rows from " + this.table);
        }
    }

    /**
     * Create a static EntityManager instance for use in test queries
     * @return em
     */
    public static EntityManager createEntityManager()
    {
        if (JPAJUnitAncestor.emf == null)
        {
            JPAJUnitAncestor.emf = Persistence.createEntityManagerFactory(JPAJUnitAncestor.PERSISTENCE_UNIT);
        }
        return JPAJUnitAncestor.emf.createEntityManager();
    }

    /**
     * @return emf
     */
    public EntityManagerFactory getEmf()
    {
        if (JPAJUnitAncestor.emf == null)
        {
            JPAJUnitAncestor.emf = Persistence.createEntityManagerFactory(JPAJUnitAncestor.PERSISTENCE_UNIT);
        }
        return JPAJUnitAncestor.emf;
    }

    /**
     * @param emfIn EntityManagerFactory for testing
     */
    public void setEmf(final EntityManagerFactory emfIn)
    {
        JPAJUnitAncestor.emf = emfIn;
    }

    /**
     * Return exiting EntityManager instance, or get a new one if needed
     * @return em
     */
    public EntityManager getEm()
    {
        if ((this.em == null || !this.em.isOpen()) && JPAJUnitAncestor.emf != null)
        {
            this.em = JPAJUnitAncestor.emf.createEntityManager();
        }
        return this.em;
    }

    /**
     * @param emIn EntityManager
     */
    public void setEm(final EntityManager emIn)
    {
        this.em = emIn;
    }

    /**
     * @return entityName
     */
    public String getEntityName()
    {
        return this.entityName;
    }

    /**
     * @param entityNameIn Entity Name for criteria queries
     */
    public void setEntity(final String entityNameIn)
    {
        this.entityName = entityNameIn;
    }

    /**
     * @return PERSISTENCE_UNIT
     */
    public static String getPersistenceUnit()
    {
        return JPAJUnitAncestor.PERSISTENCE_UNIT;
    }

    /**
     * To be used by descendant after initializing the class LOGGER
     * @param logger log4j Logger
     */
    public static void setLOGGER(Logger logger)
    {
        LOGGER = logger;
    }
}