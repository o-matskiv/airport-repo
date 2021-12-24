package helsinki.personnel;

import static ua.com.fielden.platform.reflection.TitlesDescsGetter.getEntityTitleAndDesc;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.expr;

import helsinki.security.tokens.persistent.Person_CanModify_user_Token;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.Calculated;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.Unique;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.query.model.ExpressionModel;
import ua.com.fielden.platform.property.validator.EmailValidator;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.utils.Pair;


/**
 * An entity type representing a person.
 *
 * @author Generated
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle(value = "Email", desc = "Uniquely identifies a person.")
@DescTitle(value = "Full Name", desc = "Person's full name - e.g. the first name followed by the middle initial followed by the surname.")
@MapEntityTo
@CompanionObject(PersonCo.class)
@DisplayDescription
public class Person extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = getEntityTitleAndDesc(Person.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @CompositeKeyMember(1)
    @MapTo
    @Title(value = "Email", desc = "Uniquely identifies a person.")
    @BeforeChange(@Handler(EmailValidator.class))
    private String email;

    
    @IsProperty
    @MapTo
    @Required
    @Title(value="First Name",desc="Represents the person's first")
    private String name;
    
    @IsProperty
    @MapTo
    @Required
    @Title(value="Surname",desc="Represents the person's sur")
    private String surname;
    
    
    @IsProperty
    @Readonly
    @Calculated
    @Title(value="Full Name",desc="Represents full")
    private String desc;
    protected static final ExpressionModel desc_ = expr().concat().prop("name").with().val(" ").with().prop("surname").end().model();
    
    @IsProperty
    @Unique
    @MapTo
    @Title(value = "User", desc = "An application user associated with the current person.")
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private User user;

    @IsProperty
    @MapTo
    @Title(value = "Title", desc = "Person's role, position or title.")
    private String title;

    @IsProperty
    @MapTo
    @Title(value = "Employee No", desc = "An employee number allocated to a person by their organisation.")
    private String employeeNo;

    @IsProperty
    @MapTo
    @Title(value = "Phone", desc = "An telephone number, usually representing a work office land line number.")
    private String phone;

    @IsProperty
    @MapTo
    @Title(value = "Mobile", desc = "A mobile phone number for this person.")
    private String mobile;

    
   
    @Override
    @Observable
    public Person setDesc(final String desc) {
        this.desc=desc;
        return this;
    }
    
    public String getDesc() {
        return desc;
    }


    @Observable
    public Person setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    @Observable
    public Person setMobile(final String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    @Observable
    public Person setPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    @Observable
    public Person setEmployeeNo(final String employeeNo) {
        this.employeeNo = employeeNo;
        return this;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    @Observable
    public Person setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Override
    @Observable
    public Person setActive(final boolean active) {
        super.setActive(active);
        return this;
    }

    @Observable
    @Authorise(Person_CanModify_user_Token.class)
    public Person setUser(final User user) {
        this.user = user;
        return this;
    }

    public User getUser() {
        return user;
    }

    /** A convenient method to identify whether the current person instance is an application user. */
    public boolean isAUser() {
        return getUser() != null;
    }

    @Observable
    public Person setSurname(final String surname) {
        this.surname=surname;
        return this;
    }
    
    public String getSurname() {
        return surname;
    }
    
    @Observable
    public Person setName(final String name) {
        this.name=name;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
}