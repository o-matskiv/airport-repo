package helsinki.security.tokens.persistent;

import ua.com.fielden.platform.security.tokens.Template;
import helsinki.assets.AssetType;
import helsinki.security.tokens.UsersAndPersonnelModuleToken;
import static java.lang.String.format;

public class AssetType_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), AssetType.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), AssetType.ENTITY_TITLE);
}