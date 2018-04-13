package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.awt.*;
import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNatsTasks.WalkingToNats.BankToWild2;
import static osrs.RWildyNats.WildyNats.*;

public class PreparingToGrab extends Task {
    private BasicQuery<GroundItem> bQuery;
    public static boolean ZammyAltarPrepareGrabber = false;
    public PreparingToGrab(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        final GroundItem wine = ctx.groundItems.select().id(561).nearest().poll();
        bQuery = ctx.groundItems.select().id(Nature_Rune_ID);
        return !pked && BankToWild2[BankToWild2.length-1].distanceTo(ctx.players.local())<2
                && ctx.widgets.component(218, 20).borderThickness() == 0
                && ctx.inventory.select().id(554).count() > 0
                && ctx.inventory.select().id(563).count(true) > 1;
    }

    @Override
    public void execute() {
        CurrentTask = "Preparing telegrab";

        ctx.input.speed((int)(Random.nextInt(50,62)*factorresizable));
        if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB)){
            ctx.game.tab(Game.Tab.MAGIC,true);
        }
        GroundItem wine = bQuery.nearest().poll();
        if(ctx.players.local().inCombat()) {
            pked=true;
        }
        if(ctx.widgets.component(218,20).borderThickness() != 1 && ctx.inventory.select().count() != 28) {

            if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB)){
                ctx.magic.cast(Magic.Spell.TELEKINETIC_GRAB);
            }

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.widgets.component(218,20).borderThickness() == 1;
                }
            },100,10);

            if(ctx.magic.ready(Magic.Spell.TELEKINETIC_GRAB)){
                ctx.game.tab(Game.Tab.LOGOUT,true);
                ctx.widgets.widget(69).component(19).hover();
            }

            if(!ctx.worlds.open()) {
                if(ctx.widgets.component(218, 20).borderThickness() != 0) { // if the telegrab spell is selected click somewhere in the magebook to deselect it
                    ctx.input.move(665, 350);
                    ctx.input.click(665,350,true);
                }
                ctx.worlds.open();

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.worlds.open();
                    }
                });
            }
            if (!wine.valid()) {
                if (lastItemPoint.equals(UNINITIALIZED_POINT)) {
                    final Tile wineLocation = new Tile(2950, 3824);
                    Point point = wineLocation.matrix(ctx).centerPoint();
                    point.y = point.y-9;
                    ctx.input.move(point);
                } else {
                    lastItemPoint.y = lastItemPoint.y -8;
                    ctx.input.move(lastItemPoint);
                }
            } else {
                if (ctx.camera.yaw() < 220 || ctx.camera.yaw() > 270 || ctx.camera.pitch() > 30) {
                    ctx.camera.pitch(Random.nextInt(0,30));
                    ctx.camera.angle(Random.nextInt(200,270));
                }
            }
        }

    }
}
