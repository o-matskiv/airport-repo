package helsinki.assets;

import static org.junit.Assert.*;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetch;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.math.BigDecimal;

import org.junit.Test;

import ua.com.fielden.platform.dao.QueryExecutionModel;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.entity.query.model.OrderingModel;
import ua.com.fielden.platform.error.Result;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.test.ioc.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;
import helsinki.common.validators.NoSpacesValidator;
import helsinki.personnel.Person;
import helsinki.personnel.PersonCo;
import helsinki.test_config.AbstractDaoTestCase;


/**
* Bsic testing
 * 
 * @author Helsinki Team
 *
 */
public class AssetClassTest extends AbstractDaoTestCase {

 
    @Test
    public void a_simple() {
        final var assetClass = co(AssetClass.class).new_();
        assetClass.setName("Building");
        assetClass.setDesc("Pro, build, car");
        final var savedAssetClass = co(AssetClass.class).save(assetClass);
        assertNotNull(savedAssetClass);
    	assertTrue(savedAssetClass.isActive());
    	assertEquals("Building", savedAssetClass.getName());
    }
    
    @Test
    public void can_access() {
        co(AssetClass.class).save(co(AssetClass.class).new_().setName("Building").setDesc("Pro, build, car"));
        final var assetClass = co(AssetClass.class).findByKeyAndFetch(AssetClassCo.FETCH_PROVIDER.fetchModel(), "Building");
        assertTrue(assetClass.isActive());
    }
    @Test
    public void name_cannot_50() {
        final var longname = "Building".repeat(50);
        final var assetClass = co(AssetClass.class).new_().setName(longname).setDesc("Pro, build, car");
        final MetaProperty<String> mpName= assetClass.getProperty("name");
        assertNull(mpName.getValue());
        assertNull(assetClass.getName());
        assertFalse(mpName.isValid());
        
        final Result validationResult = mpName.getFirstFailure();
        //validationResult.isSuccessful();
        assertNotNull(validationResult);
        assertFalse(validationResult.isSuccessful());
        //System.out.println(validationResult.getMessage());
        assertEquals("Value should not be longer than 50 characters.",validationResult.getMessage());
    }
    
    @Test
    public void name_cannot_whitespaces() {
        final var assetClass = co(AssetClass.class).new_().setName("Building").setDesc("Pro, build, car");
        assetClass.setName("Name with spaces");
        final MetaProperty<String> mpName= assetClass.getProperty("name");

        assertFalse(mpName.isValid());
        final Result validationResult = mpName.getFirstFailure();

        assertEquals(NoSpacesValidator.ERR_SPACES,validationResult.getMessage());
        assertEquals("Building", assetClass.getName());
        assetClass.setName("Building1");
        assertTrue(mpName.isValid());
        assertEquals("Building1", assetClass.getName());


    }
    
    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

    
    @Override
    public boolean useSavedDataPopulationScript() {
        return true;
    }

    /**
     * Domain state population method.
     * <p>
     * <b>IMPORTANT:</p> this method executes only once for a Test Case. At the same time, new instances of a Test Case are created for each test method.
     * Thus, this method should not be used for initialisation of the Test Case state other than the persisted domain state.
     */
    @Override
    protected void populateDomain() {
        // Need to invoke super to create a test user that is responsible for data population 
    	super.populateDomain();

    	// Here is how the Test Case universal constants can be set.
    	// In this case the notion of now is overridden, which makes it possible to have an invariant system-time.
    	// However, the now value should be after AbstractDaoTestCase.prePopulateNow in order not to introduce any date-related conflicts.
    	final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
    	constants.setNow(dateTime("2019-10-01 11:30:00"));

    	// If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }

   }

}
