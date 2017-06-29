// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: RSM75NewModel::org::papnewgen::PapyrusServices
 * STEREOTYPE:  Service
 */
package org.papnewgen;

import java.util.Collection;

/**
 * @see org.papnewgen.PapyrusServices
 */
public class PapyrusServicesImpl
    extends PapyrusServicesBase
{

    /**
     * @see org.papnewgen.PapyrusServices#getAll()
     */
    @Override
    protected  ModelSimpleVo[] handleGetAll()
        throws Exception
    {
    	Collection models = getModelDao().loadAll(ModelDao.TRANSFORM_MODELSIMPLEVO);
        return (ModelSimpleVo[]) models.toArray(new ModelSimpleVo[models.size()]);
    }

}