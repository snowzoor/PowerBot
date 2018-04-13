package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Magic;
import org.powerbot.script.rt4.Npc;
import osrs.RWildyNats.WildyNats;

import java.awt.*;
import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNatsTasks.WalkingToNats.BankToWild2;
import static osrs.RWildyNats.WildyNats.*;

public class GrabbingNats extends Task {

    public static boolean ZammyAltarWineGrabber = false;




    public GrabbingNats(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final GroundItem nats = ctx.groundItems.select().id(561).at(new Tile(3306, 3860)).poll();
        return (!pked &&ctx.inventory.select().count() < 28
                && ctx.inventory.select().id(LAWRUNE_ID).count(true) != 1
                && BankToWild2[BankToWild2.length-1].distanceTo(ctx.players.local()) <2);
    }

    @Override
    public void execute() {
        CurrentTask = "Grabbing nats";
        ctx.input.speed((int)(Random.nextInt(50,68)*factorresizable));
        if(ctx.players.local().inCombat()) {
            pked=true;
        }

        final Npc randomevt= ctx.npcs.select().within(5d).action("Dismiss").select(new Filter<Npc>() {
            @Override
            public boolean accept(final Npc npc) {
                return npc.interacting().equals(ctx.players.local());
            }
        }).nearest().poll();
        if(randomevt.valid()) {
            if(ctx.magic.spell()!= Magic.Spell.NIL){
                ctx.input.click(ctx.widgets.widget(320).component(Random.nextInt(1,24)).component(0).centerPoint(),true);
                Condition.wait(new Condition.Check() {
                    @Override
                    public boolean poll() {
                        return ctx.magic.spell()== Magic.Spell.NIL;
                    }
                },200,10);
            }
            randomevt.interact(false,"Dismiss");
            Condition.wait(new Condition.Check() {
                @Override
                public boolean poll() {
                    return !randomevt.valid();
                }
            });
        }
        final GroundItem nats = ctx.groundItems.select().id(561).nearest().poll();
        final int oldinventory = ctx.inventory.select().id(Nature_Rune_ID).count(true);
        // We want to constantly check if the groundItem wine exists, so that, if there is competetion(other players) we will be faster than they are
        if(nats.valid()) { // We only want to check for these actions if wine.valid() -> in this way the client doesnt have to execute every single step everytime the function is activated but instead it only executes these steps when there is a wine nearby
            if (ctx.camera.yaw() < 220 || ctx.camera.yaw() > 270 || ctx.camera.pitch() > 30) {
                ctx.camera.pitch(Random.nextInt(0,30));
                ctx.camera.angle(Random.nextInt(200,270));
            }
            Point poin2 = nats.centerPoint();
        //poin2.y = poin2.y-7;
        ctx.input.move(poin2);
        Condition.sleep(Random.nextInt(400,700));
        ctx.input.click(poin2,true);

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(Nature_Rune_ID).count(true) != oldinventory;
                }
            },100,30);



        }

        if(ctx.inventory.select().id(Nature_Rune_ID).count(true) != oldinventory) {
            WildyNats.wines = WildyNats.wines + (ctx.inventory.select().id(Nature_Rune_ID).count(true) - oldinventory);
            System.out.println(WildyNats.wines);
        }
    }
}
