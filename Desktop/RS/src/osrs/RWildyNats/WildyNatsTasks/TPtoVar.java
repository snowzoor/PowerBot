package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Magic;

import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNatsTasks.WalkingToWild20.pathToWildylevel20;
import static osrs.RWildyNats.WildyNats.CurrentTask;

public class TPtoVar extends Task {
    public static int FALLYTP_ID = 8009;


    public TPtoVar(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {


        return pathToWildylevel20[pathToWildylevel20.length - 1].distanceTo(ctx.players.local()) < 2
                || ctx.inventory.select().id(563).count()<2 && pathToWildylevel20[pathToWildylevel20.length - 1].distanceTo(ctx.players.local()) < 2;
    }

    @Override
    public void execute() {
        CurrentTask = "TP to varrock";
        final int oldinv=ctx.inventory.select().count();

            Condition.sleep(Random.getDelay());

            if(!ctx.game.tab(Game.Tab.MAGIC,true)) {
                ctx.game.tab(Game.Tab.MAGIC,true);
            }
            Condition.sleep(Random.getDelay());
            ctx.magic.cast(Magic.Spell.VARROCK_TELEPORT);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().count() !=oldinv;
                }
            },100,20);
        }

}
