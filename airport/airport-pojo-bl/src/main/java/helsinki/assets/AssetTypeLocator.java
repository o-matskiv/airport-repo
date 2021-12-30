package helsinki.assets;

import ua.com.fielden.platform.entity.NoKey;
import static ua.com.fielden.platform.entity.NoKey.NO_KEY;
import helsinki.assets.AssetType;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;

/**
 * Locator entity object.
 *
 * @author Developers
 *
 */
@KeyType(NoKey.class)
@CompanionObject(AssetTypeLocatorCo.class)
public class AssetTypeLocator extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    public AssetTypeLocator() {
        setKey(NO_KEY);
    }

    @IsProperty
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private AssetType assetType;

    @Observable
    public AssetTypeLocator setAssetType(final AssetType value) {
        this.assetType = value;
        return this;
    }

    public AssetType getAssetType() {
        return assetType;
    }

}
