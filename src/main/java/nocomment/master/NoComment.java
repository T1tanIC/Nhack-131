package nocomment.master;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import me.bebeli555.cookieclient.Mod;
import me.bebeli555.cookieclient.gui.Group;
import nocomment.master.db.Database;
import nocomment.master.util.LoggingExecutor;
import nocomment.master.util.Telegram;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class NoComment extends Mod  {

    public static Executor executor = new LoggingExecutor(Executors.newFixedThreadPool(1), "main");
    public static final boolean DRY_RUN = false;

    public static void main(String[] args) throws Exception {
        Telegram.INSTANCE.startup();
        if (!DRY_RUN) {
            new HTTPServer(0000);
            DefaultExports.initialize();
            new Database();
            System.exit(1);
        }
    }

    public NoComment() {

        super(Group.HAE, "NAPP", "", "");
    }
}
