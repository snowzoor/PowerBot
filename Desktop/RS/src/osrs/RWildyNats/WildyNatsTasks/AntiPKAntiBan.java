package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.*;

import static osrs.RWildyNats.WildyNatsTasks.WalkingToNats.BankToWild2;
import static osrs.RWildyNats.WildyNats.CurrentTask;
import static osrs.RWildyNats.WildyNats.pked;

public class AntiPKAntiBan extends Task {
    int decider;
    public static boolean hop = false;
    public AntiPKAntiBan(ClientContext ctx) {
        super(ctx);
    }
    int[] worlds = {26,35,82,83,93,94};
    int randomworld;

    @Override
    public boolean activate() {
        final int currentwildylevel;

        String wildylevel = ctx.widgets.widget(90).component(46).text();
        if(wildylevel.length()<7) {
            currentwildylevel = 45;
        } else {
            currentwildylevel = Integer.parseInt(wildylevel.substring(7,wildylevel.length()));
        }
        ctx.input.speed(1);
    PlayerQuery player = ctx.players.select().within(23).select(new Filter<Player>() {
            public boolean accept(Player player) {

                return !player.equals(ctx.players.local()) && player.combatLevel() < (ctx.players.local().combatLevel()+currentwildylevel+50)
                        || !player.equals(ctx.players.local()) && !player.overheadMessage().isEmpty(); //player.combatLevel() < (ctx.players.local().combatLevel()+38)

            }

        });

// only true if we are at the zammy altar and if there are players within a radius of 15 tiles that are able to attack us
        return !pked &&!player.isEmpty() && !ctx.players.local().inCombat() && BankToWild2[BankToWild2.length-1].distanceTo(ctx.players.local())<40;
    }
// && pathToWildylevel20[pathToWildylevel20.length-1].distanceTo(ctx.players.local())>3 && !ctx.players.local().inCombat() && ctx.inventory.select().elderanimationsstorage() >3;
    @Override
    public void execute() {
        CurrentTask = "WorldHopAntiBan";
        ctx.input.speed(1);

        if(ctx.magic.spell()!= Magic.Spell.NIL){
            ctx.input.click(true);
            //ctx.input.click(ctx.widgets.widget(320).component(Random.nextInt(1,24)).component(0).centerPoint(),true);
            Condition.wait(new Condition.Check() {
                @Override
                public boolean poll() {
                    return ctx.magic.spell()== Magic.Spell.NIL;
                }
            },200,10);
        }
        if(!ctx.worlds.open()) {
            ctx.worlds.open();

        }
        if(ctx.players.local().inCombat()) {
            pked=true;
        }
        if(ctx.worlds.open()) {
            ctx.widgets.widget(69).component(19).click();
        }else{
            ctx.worlds.open();
            ctx.widgets.widget(69).component(19).click();
        }
        ctx.properties.setProperty("login.disable", "true");
        Condition.sleep(Random.nextInt(2*60*1000, 5*60*1000));
        ctx.properties.setProperty("login.disable", "false");




           /* String world = ctx.widgets.widget(429).component(1).text();
            int currentworld = Integer.parseInt(world.substring(22, 24));
            System.out.println(currentworld);



        /*final World random;
        random = new World((ctx), randomworld, NIL.size(), World.Type.FREE, World.Server.RUNE_SCAPE, World.Specialty.NONE);
s
            random.hop();*/

       /* if (currentworld < 35) {
            randomworld = worlds[Random.nextInt(0, 2)];
            if (randomworld == currentworld) {
                randomworld = worlds[Random.nextInt(0, 2)];
            }
        }
        else{
            randomworld = worlds[Random.nextInt(3, 5)];

            if (randomworld == currentworld) {
                randomworld = worlds[Random.nextInt(3, 5)];
            }*/



        //Condition.wait(() -> currentworld==randomworld,100,10);

        //hop = true;


        //hop = false;

        ctx.input.speed(Random.nextInt(60,75));
    }

}
