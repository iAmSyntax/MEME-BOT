import com.example.jda.Bot.MemeCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

// Main bot class

public class Bot {

    public Bot() throws Exception {
        JDA api = JDABuilder.createDefault("ENTER THE TOKEN HERE").build();
        api.getPresence().setStatus(OnlineStatus.ONLINE);
        api.getPresence().setActivity(Activity.listening(" I AM A MEME LORD"));
        api.addEventListener(new MemeCommands());


    }

    public static void main(String[] args) throws Exception {
        new Bot();
    }
}


