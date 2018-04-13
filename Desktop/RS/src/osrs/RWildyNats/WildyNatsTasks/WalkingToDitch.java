package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNats.*;

public class WalkingToDitch extends Task {
    public static final Tile[] BankToWild = {new Tile(3253, 3420, 0), new Tile(3253, 3424, 0), new Tile(3250, 3428, 0), new Tile(3248, 3432, 0), new Tile(3246, 3436, 0), new Tile(3246, 3440, 0), new Tile(3246, 3444, 0), new Tile(3246, 3448, 0), new Tile(3246, 3452, 0), new Tile(3246, 3456, 0), new Tile(3245, 3460, 0), new Tile(3245, 3464, 0), new Tile(3245, 3468, 0), new Tile(3245, 3472, 0), new Tile(3245, 3476, 0), new Tile(3245, 3480, 0), new Tile(3245, 3484, 0), new Tile(3245, 3488, 0), new Tile(3245, 3492, 0), new Tile(3245, 3496, 0), new Tile(3245, 3500, 0), new Tile(3245, 3504, 0), new Tile(3244, 3508, 0), new Tile(3244, 3512, 0), new Tile(3244, 3516, 0), new Tile(3243, 3520, 0)};

    public static int WILDERNESS_DITCH = 23271;
    public  static  boolean CROSSED = false;

    private final Walker walker = new Walker(ctx);
    public WalkingToDitch(ClientContext ctx) {
        super(ctx);


    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(LAWRUNE_ID).count(true) > 1 && ctx.inventory.select().id(FIRERUNE_ID).count(true) > 0 && BankToWild[0].distanceTo(ctx.players.local()) < 300
                && BankToWild[BankToWild.length - 1].distanceTo(ctx.players.local()) >= 0 && ctx.players.local().tile().y() < 3521;
    }

    @Override
    public void execute() {
        ctx.input.speed((int)(Random.nextInt(50,68)*factorresizable));
        CurrentTask = "Walking To Ditch";
        int random2 = org.powerbot.script.Random.nextInt(5,12);
        if(random2==8) {
            ctx.camera.pitch(org.powerbot.script.Random.nextInt(50, 99));
            ctx.camera.angle(org.powerbot.script.Random.nextInt(0, 360));

        }
        /*if(!ctx.movement.running() && ctx.movement.energyLevel() > org.powerbot.script.Random.nextInt(20,60)) {
            ctx.widgets.component(160,27).click();
        }*/
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)
                || ctx.movement.destination().distanceTo(ctx.players.local()) < 9) {
            if (ctx.inventory.select().count() >1) {
                if(!ctx.widgets.widget(475).component(0).visible())
                    walker.walkPath(BankToWild);
                ctx.objects.select(1).id(WILDERNESS_DITCH).poll().click();
                if(ctx.widgets.widget(475).component(0).visible()){
                    ctx.widgets.widget(475).component(11).click();
                }
                CROSSED = true;
                Tile tile = ctx.players.local().tile();
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return tile != ctx.players.local().tile();
                    }

                }, 100, 20);
            }
        }





        WalkingToLumbyBank.hop = false;
        if(!ctx.worlds.open()) {
            ctx.worlds.open();

        }
    }
}