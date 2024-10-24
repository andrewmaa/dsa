import java.io.*;

public class Browser {
    final private UrlInfo homepage;
    
    // Stack for back/forward history
    private Stack<UrlInfo> pageBackStack = new ArrayStack<>();
    private Stack<UrlInfo> pageForwardStack = new ArrayStack<>();
    private UrlInfo currentPage;

    // Stack for prev/next on page
    private Stack<String> framePrevStack = new ArrayStack<>();
    private Stack<String> frameNextStack = new ArrayStack<>();
    private String currentFrame;

    private TextRenderer renderer;

    public Browser(UrlInfo homepage, int numLinesAtATime) {
        this.homepage = homepage;
        this.renderer = new TextRenderer(numLinesAtATime);
    }

    public static void main(String[] args) throws IOException {
        int numLinesAtATime = Integer.parseInt(args[0]);

        String homepage = "info.cern.ch/hypertext/WWW/index.html";
        UrlInfo homepageUrlInfo = new UrlInfo(homepage, null);

        Browser browser = new Browser(homepageUrlInfo, numLinesAtATime);
        System.out.println(browser.runCommand("HOME"));

        // Being the Read-Eval-Print Loop (or REPL)
        while (true) {
            // Read
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine().trim().toUpperCase();
            
            // Eval
            String output = browser.runCommand(input);

            // Print
            System.out.println(output);
        }
    }
    // function to go to the next frame
    public String goNext() {

        // if the stack is not empty, pop the next frame
        // if the current frame is not null, push it to the prev stack
        // set the current frame to the next frame
        if (!this.frameNextStack.isEmpty()) {
            if (this.currentFrame != null) {
                this.framePrevStack.push(this.currentFrame);
            }
            this.currentFrame = this.frameNextStack.pop();
        }
        else {
            // if the renderer has a next frame, set the current frame to the next frame
            // if the current frame is not null, push it to the prev stack
            if (this.renderer.hasNextFrame()) {
                if (this.currentFrame != null) {
                    this.framePrevStack.push(this.currentFrame);
                }
                this.currentFrame = this.renderer.nextFrame();
            }

            // otherwise, return that the end of the page has been reached
            else {
                return "End of page reached";
            }
        }

        return this.currentFrame;
    }

    public String navPage(UrlInfo page) {
        // if the renderer cannot load the page, return that it failed
        if (!this.renderer.newPage(page)) {
            return "Failed to load the page.";
        }

        // if the current page is not null, push it to the back stack
        if (this.currentPage != null) {
            this.pageBackStack.push(this.currentPage);
        }

        // set the current page to the new page
        this.currentPage = page;

        // clear the forward stack
        this.pageForwardStack.clear();

        // go to the next frame
        return this.goNext();
    }

    public String runCommand(String command) {
        switch (command.toLowerCase()) {
            case "":
            case "next":
                // go to the next frame
                return this.goNext();
            case "home":
                // go to the home page
                return this.navPage(this.homepage);
            case "prev":
                // if the prev stack is not empty, pop the previous frame
                // if the current frame is not null, push it to the next stack
                // set the current frame to the previous frame
                if (!this.framePrevStack.isEmpty()) {
                    if (this.currentFrame != null) {
                        this.frameNextStack.push(this.currentFrame);
                    }
                    this.currentFrame = this.framePrevStack.pop();
                }
                else {
                    // if the top of the page has been reached, return that it has
                    return "Top of page reached";
                }

                // return the current frame
                return this.currentFrame;
            case "reload":
                // reload the current page
                return this.navPage(this.currentPage);  
            case "back":    
                // if the back stack is not empty, pop the previous page
                // if the current page is not null, push it to the forward stack
                // set the current page to the previous page
                if (!this.pageBackStack.isEmpty()) {
                    if (this.currentPage != null) {
                        this.pageForwardStack.push(this.currentPage);
                    }
                    this.currentPage = this.pageBackStack.pop();
                }
                else {
                    // if there is no page to go back to, return that it failed
                    return "No page to go back to.";
                }

                // return the current page
                return this.navPage(this.currentPage);     
            case "forward":
                // if the forward stack is not empty, pop the next page
                // if the current page is not null, push it to the back stack
                // set the current page to the next page
                if (!this.pageForwardStack.isEmpty()) {
                    if (this.currentPage != null) {
                        this.pageBackStack.push(this.currentPage);
                    }
                    this.currentPage = this.pageForwardStack.pop();
                }
                else {
                    // if there is no page to go forward to, return that it failed
                    return "No page to go forward to.";
                }
                
                // return the current page
                return this.navPage(this.currentPage);
            case "help":
                // return the help menu
                return showHelp();
            case "links":
                // format the links
                StringBuffer sb = new StringBuffer();

                // for each link, format it and append it to the string buffer
                for (int i = 0; i < this.renderer.links.size(); i++) {
                    sb.append(this.formatLink(i, this.renderer.links.get(i)) + "\n");
                }

                // return the formatted links
                return sb.toString();

            case "exit":
            case "quit":
                // exit the program (both 'exit' and 'quit' are valid commands)
                System.exit(0);
                return "";
            default:
                // if the command is a number, navigate to the link
                if (isNumber(command)) {
                    // parse the command as an integer
                    int com = Integer.parseInt(command);

                    // if the command is not within the range of links, return that it is invalid
                    if (!((com >= 0) && (com <= this.renderer.links.size()))) {
                        return "Invalid link";
                    }
                    else {
                        // navigate to the link
                        UrlInfo newUrl = UrlInfo.navigate(this.currentPage.toString(), this.renderer.links.get(com));
                        return this.navPage(newUrl);
                    }
                } else {
                    // if the input is not a valid number or command, return that it is invalid
                    return "Invalid command. Type HELP for a list of commands.";    
                }
        }
    }

    private String formatLink(int number, String link) {
        return String.format("[%d] %s", number, link);
    }

    // Show help menu
    private String showHelp() {
        StringBuffer sb = new StringBuffer();
        sb.append("Commands Available:\n");
        sb.append("HOME: Go to the home page\n");
        sb.append("NEXT (or no command): Go to the next frame of text.\n");
        sb.append("PREV: Re-render the previous frame of the current page.\n");
        sb.append("<number>: Go to the link associated with the number.\n");
        sb.append("BACK: Go back to the previous page.\n");
        sb.append("FORWARD: Go forward to the next page.\n");
        sb.append("LINKS: Show the list of links available on the current page.\n");
        sb.append("HELP: Show this help menu.\n");

        return sb.toString();
    }

    private boolean isNumber(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
