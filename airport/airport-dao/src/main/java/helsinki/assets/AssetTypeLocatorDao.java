package helsinki.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link AssetTypeLocatorCo}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeLocator.class)
public class AssetTypeLocatorDao extends CommonEntityDao<AssetTypeLocator> implements AssetTypeLocatorCo {

    @Inject
    public AssetTypeLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetTypeLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}