package helsinki.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.security.tokens.Template;
import helsinki.assets.AssetClass;
import helsinki.security.tokens.UsersAndPersonnelModuleToken;


/**
 * A security token for entity {@link AssetClass} to guard Delete.
 *
 * @author Helsinki team
 *
 */
public class AssetClass_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), AssetClass.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), AssetClass.ENTITY_TITLE);
}