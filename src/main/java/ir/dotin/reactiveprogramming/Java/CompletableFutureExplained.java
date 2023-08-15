package ir.dotin.reactiveprogramming.Java;

import ir.dotin.reactiveprogramming.util.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExplained {

    // two methods to simulate fetching data from a remote server.
    // we use supplyAsync to execute these task asynchronously.
    private static CompletableFuture<String> getUserName() {
        return CompletableFuture.supplyAsync(() -> {
            Utils.sleepInSeconds(2);
            return "Mehdi";
        });
    }

    private static CompletableFuture<String> getEmail() {
        return CompletableFuture.supplyAsync(() -> {
            Utils.sleepInSeconds(2);
            return "mojtahedwork@gmail.com";
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> fetchedUserName = getUserName();
        CompletableFuture<String> fetchedEmail = getEmail();

        // this method will allow us to wait until both promises finish doing their job.
        CompletableFuture<Void> combinedFeatures = CompletableFuture.allOf(fetchedEmail, fetchedUserName);

        // thenApplyAsync method applies a function to the combined result.
        CompletableFuture<String> fetchedCompleteUserInfo = combinedFeatures.thenApplyAsync((Void) -> {
            try {
                String userName = fetchedUserName.get();
                String email = fetchedEmail.get();

                return "Username : " + userName + " Email : " + email;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error Happened.";
            }
        });

        try {
            String userInfo = fetchedCompleteUserInfo.get();
            System.out.println(userInfo);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


}
