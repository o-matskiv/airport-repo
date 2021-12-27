package helsinki.webapp.config.assets;

import static java.lang.String.format;
import static helsinki.common.LayoutComposer.*;
import static helsinki.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static helsinki.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static helsinki.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import java.util.Optional;
import com.google.inject.Injector;
import helsinki.assets.AssetClass;
import helsinki.common.LayoutComposer;
import helsinki.common.StandardActions;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import helsinki.main.menu.assets.MiAssetClass;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;


public class AssetClassWebUiConfig {

 public final EntityCentre<AssetClass> centre;
public final EntityMaster<AssetClass> master;

 public static AssetClassWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
 return new AssetClassWebUiConfig(injector, builder);
}

 private AssetClassWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
 centre = createCentre(injector, builder);
 builder.register(centre);
 master = createMaster(injector);
 builder.register(master);
}

 
private EntityCentre<AssetClass> createCentre(final Injector injector, final IWebUiBuilder builder) {
 final String layout = cell(
cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN)) 
.cell(cell(CELL_LAYOUT)),
PADDING_LAYOUT).toString();

final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetClass.class);
 final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetClass.class);
 final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetClass.class);
 final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetClass.class);
 final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
 builder.registerOpenMasterAction(AssetClass.class, standardEditAction);

final EntityCentreConfig<AssetClass> ecc = EntityCentreBuilder.centreFor(AssetClass.class)
.addFrontAction(standardNewAction)
.addTopAction(standardNewAction).also()
.addTopAction(standardDeleteAction).also()
.addTopAction(standardSortAction).also()
.addTopAction(standardExportAction)
.addCrit("this").asMulti().autocompleter(AssetClass.class).also()
.addCrit("active").asMulti().bool().also()
.addCrit("desc").asMulti().text()
.setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
.setLayoutFor(Device.TABLET, Optional.empty(), layout)
.setLayoutFor(Device.MOBILE, Optional.empty(), layout)
.withScrollingConfig(standardStandaloneScrollingConfig(0))
.addProp("this").order(1).asc().width(100)
 .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetClass.ENTITY_TITLE)).also()
.addProp("desc").minWidth(300).also()
.addProp("active").width(50)
.addPrimaryAction(standardEditAction)
.build();

return new EntityCentre<>(MiAssetClass.class, ecc, injector);
}


private EntityMaster<AssetClass> createMaster(final Injector injector) {
 final String layout = cell(
cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN)). 
cell(cell(CELL_LAYOUT)), //row 2 -> 3
PADDING_LAYOUT).toString();

final IMaster<AssetClass> masterConfig = new SimpleMasterBuilder<AssetClass>().forEntity(AssetClass.class)
.addProp("name").asSinglelineText().also()
.addProp("active").asCheckbox().also()
.addProp("desc").asMultilineText().also()
.addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
.addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
.setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
.setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
.setLayoutFor(Device.TABLET, Optional.empty(), layout)
.setLayoutFor(Device.MOBILE, Optional.empty(), layout)
.withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 280, Unit.PX))
.done();

return new EntityMaster<>(AssetClass.class, masterConfig, injector);
}
} 