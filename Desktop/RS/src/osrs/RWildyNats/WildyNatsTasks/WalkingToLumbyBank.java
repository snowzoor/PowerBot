package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.World;

import static org.powerbot.script.rt4.World.NIL;
import static osrs.RWildyNats.WildyNats.CurrentTask;
import static osrs.RWildyNats.WildyNats.pked;

public class WalkingToLumbyBank extends Task {
    public static final Tile[] DeathWalkToLumbyBank = {new Tile(3221, 3222, 0), new Tile(3222, 3219, 0), new Tile(3219, 3219, 0), new Tile(3216, 3219, 0), new Tile(3215, 3216, 0), new Tile(3215, 3213, 0), new Tile(3212, 3211, 0), new Tile(3209, 3211, 0), new Tile(3206, 3209, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3212, 2), new Tile(3205, 3215, 2), new Tile(3206, 3218, 2), new Tile(3209, 3220, 2)};
    private final Walker walker = new Walker(ctx);
    public static boolean hop = false;
    int decider;
    public WalkingToLumbyBank(ClientContext ctx) {
        super(ctx);
    }



    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 2  && DeathWalkToLumbyBank[DeathWalkToLumbyBank.length-1].distanceTo(ctx.players.local())>2
                && DeathWalkToLumbyBank[0].distanceTo(ctx.players.local())<40 || ctx.players.local().tile().floor()>0
                && DeathWalkToLumbyBank[DeathWalkToLumbyBank.length-1].distanceTo(ctx.players.local())>2;
    }

    @Override
    public void execute() {
        CurrentTask = "Deathwalk";
        pked=false;
        ctx.input.speed(Random.nextInt(60,75));
        int a = 26;
        int b = 35;
        int c = 82;
        int d = 94;
        int e = Random.nextInt(83,84);
        decider =Random.nextInt(1,5);
        final World random;
        if(decider == 1) {
            random = new World((ctx), a, NIL.size(), World.Type.FREE, World.Server.GERMANY, World.Specialty.NONE);
        } else if(decider == 2) {
            random = new World((ctx), b, NIL.size(), World.Type.FREE, World.Server.GERMANY, World.Specialty.NONE);
        } else if(decider ==3) {
            random = new World((ctx), c, NIL.size(), World.Type.FREE, World.Server.GERMANY, World.Specialty.NONE);
        } else if(decider ==4) {
            random = new World((ctx), d, NIL.size(), World.Type.FREE, World.Server.GERMANY, World.Specialty.NONE);
        } else {
            random = new World((ctx), e, NIL.size(), World.Type.FREE, World.Server.GERMANY, World.Specialty.NONE);
        }
        //System.out.println(random);
        if(ctx.widgets.component(218, 20).borderThickness() != 0) {
            ctx.input.click(665,350,true);
        }
        if (!hop) {

            ctx.worlds.open();

            if(random.hop()) {
                hop = true;
            }
            Condition.sleep(Random.getDelay());
        }
        //System.out.println(hop);
        if(hop) {

            if(ctx.camera.pitch() <60 || ctx.camera.yaw() < 111 || ctx.camera.yaw() > 137) {
                ctx.camera.pitch(Random.nextInt(65,80));
                ctx.camera.angle(Random.nextInt(112,136));
            }
            if (DeathWalkToLumbyBank[0].distanceTo(ctx.players.local()) < 6) {


              ctx.game.tab(Game.Tab.INVENTORY);
                ctx.camera.pitch(Random.nextInt(70,90));
            }
            Condition.sleep(Random.getDelay());
            if (!ctx.movement.running() && ctx.movement.energyLevel() > org.powerbot.script.Random.nextInt(20, 60)) {
                ctx.widgets.component(160, 27).click();
            }
            if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
                if (ctx.inventory.select().count() == 2) {

                    walker.walkPath(DeathWalkToLumbyBank);

                }
            }
        }
    }

}
