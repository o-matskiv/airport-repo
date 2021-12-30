package helsinki.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import helsinki.assets.AssetType;
import ua.com.fielden.platform.security.tokens.Template;
import helsinki.assets.AssetType;
import helsinki.security.tokens.UsersAndPersonnelModuleToken;
import static java.lang.String.format;

/**
 * A security token for entity {@link AssetType} to guard Save.
 *
 * @author Developers
 *
 */
public class AssetType_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), AssetType.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), AssetType.ENTITY_TITLE);
}