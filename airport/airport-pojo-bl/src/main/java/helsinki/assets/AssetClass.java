package helsinki.assets;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * An Entity Assets can be classified correctly
 *
 * @author Helsinki team
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Asset Class")
@CompanionObject(AssetClassCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Asset Class",desc="Used for classification")

public class AssetClass extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetClass.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty(length = 50)
    @MapTo
    @Title(value = "Name",desc="A unique asset class")
    @CompositeKeyMember(1)
    @BeforeChange(@Handler(MaxLengthValidator.class))
    private String name;
    
    @Observable
    public AssetClass setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    @Observable
    @Override
    public AssetClass setDesc(String desc){
       super.setDesc(desc);
       return this;
    }
    
    @Override
    @Observable
    public AssetClass setActive(boolean active){
         super.setActive(active);
         return this;
    }
    
    
}
