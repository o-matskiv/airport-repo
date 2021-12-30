package helsinki.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetTypeLocator}.
 *
 * @author Developers
 *
 */
public interface AssetTypeLocatorCo extends IEntityDao<AssetTypeLocator> {

    static final IFetchProvider<AssetTypeLocator> FETCH_PROVIDER = EntityUtils.fetch(AssetTypeLocator.class).with("assetType");

}
