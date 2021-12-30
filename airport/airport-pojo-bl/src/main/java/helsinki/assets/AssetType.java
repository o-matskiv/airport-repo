package helsinki.assets;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import org.checkerframework.common.aliasing.qual.Unique;

import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.entity.annotation.Required;
import helsinki.security.tokens.persistent.AssetType_CanModify_assetClass_Token;
import ua.com.fielden.platform.security.Authorise;

/**
 * Master entity object.
 *
 * @author HelsinkiTeam
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(AssetTypeCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Asset Type", desc = "Classification of assets")
public class AssetType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty(length = 50)
    @MapTo
    @Title(value = "Name", desc = "A unique asset type name.")
    @CompositeKeyMember(1)
    @BeforeChange({@Handler(MaxLengthValidator.class)})
    private String name;
    
    @IsProperty
    @Required
    @MapTo
    @Title(value = "Asset Class", desc = "An asset class")
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private AssetClass assetClass;

     @Observable
    public AssetType setName(final String name) {
        this.name = name;
        return this;
    }

     public String getName() {
        return name;
    }

     @Observable
    @Override
    public AssetType setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }

    @Observable
    public AssetType setActive(boolean active) {
        super.setActive(active);
        return this;
    }

     @Observable
     @Authorise(AssetType_CanModify_assetClass_Token.class)
    public AssetType setAssetClass(final AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }

     public AssetClass getAssetClass() {
        return assetClass;
    }
}
