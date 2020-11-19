package ru.miborisov.account;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import ru.miborisov.account.dto.request.*;
import ru.miborisov.account.model.AccountException;
import ru.miborisov.account.service.AccountService;

import static ru.miborisov.account.dto.response.AccountExceptionResponse.fromAccountException;

public class Application {
    public static void main(String[] args) {
        // We could wire everything together with DI and configurations but let's keep everything simple
        String basePath = "/api/account";
        int port = 5000;

        Javalin app = Javalin.create(config -> {
            config.contextPath = basePath;
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        });

        var accountService = new AccountService();

        app.post("/create", (ctx) -> {
            var request = ctx.bodyAsClass(AccountCreate.class);
            ctx.json(accountService.create(request.email));
            ctx.res.setStatus(200);
        });

        app.post("/details", (ctx) -> {
            var request = ctx.bodyAsClass(AccountDetails.class);
            ctx.json(accountService.details(request.email));
            ctx.res.setStatus(200);
        });

        app.post("/topUp", (ctx) -> {
            var request = ctx.bodyAsClass(AccountTopUp.class);
            accountService.topUp(request.email, request.amount);
            ctx.res.setStatus(200);
        });

        app.post("/withdraw", (ctx) -> {
            var request = ctx.bodyAsClass(AccountWithdraw.class);
            accountService.withdraw(request.email, request.amount);
            ctx.res.setStatus(200);
        });

        app.post("/transfer", (ctx) -> {
            var request = ctx.bodyAsClass(AccountTransfer.class);
            accountService.transfer(request.source, request.target, request.amount);
            ctx.res.setStatus(200);
        });

        app.exception(AccountException.class, (e, ctx) -> {
            ctx.res.setStatus(400);
            ctx.json(fromAccountException(e));
        });

        app.start(port);
    }
}
