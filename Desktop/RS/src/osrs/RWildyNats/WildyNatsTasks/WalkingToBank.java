package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import static osrs.RWildyNats.WildyNats.CurrentTask;
import static osrs.RWildyNats.WildyNats.pked;

public class WalkingToBank extends Task {
    public static final Tile[] VarTPtoBankPath = {new Tile(3212, 3423, 0), new Tile(3216, 3425, 0), new Tile(3220, 3427, 0), new Tile(3224, 3428, 0), new Tile(3228, 3428, 0), new Tile(3232, 3428, 0), new Tile(3236, 3428, 0), new Tile(3240, 3428, 0), new Tile(3244, 3428, 0), new Tile(3248, 3428, 0), new Tile(3252, 3428, 0), new Tile(3253, 3424, 0), new Tile(3254, 3420, 0)};

    private final Walker walker = new Walker(ctx);
    public WalkingToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return VarTPtoBankPath[0].distanceTo(ctx.players.local())<100
                &&VarTPtoBankPath[VarTPtoBankPath.length-1].distanceTo(ctx.players.local())>3
                && ctx.inventory.select().id(554).count() == 0 && ctx.inventory.select().id(563).count() == 0
                //&& ctx.inventory.select().id(Nature_Rune_ID).count() > 0
                && !ctx.bank.opened();
    }

    @Override
    public void execute() {
        CurrentTask = "Walking To Bank";
        pked=false;
        int random2 = org.powerbot.script.Random.nextInt(6,11);
        if(random2==8) {
            ctx.camera.pitch(org.powerbot.script.Random.nextInt(50, 99));
            ctx.camera.angle(org.powerbot.script.Random.nextInt(0, 360));

        }

        /*if(!ctx.movement.running() && ctx.movement.energyLevel() > org.powerbot.script.Random.nextInt(20,60)) {
            ctx.widgets.component(160,27).click();
        }*/
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 9) {
            if (VarTPtoBankPath[0].distanceTo(ctx.players.local())<3 || VarTPtoBankPath[VarTPtoBankPath.length-1].distanceTo(ctx.players.local())>1) {

                walker.walkPath(VarTPtoBankPath);

            }
        }
    }
}
