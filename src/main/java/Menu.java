import exceptions.TwitNotFoundException;
import models.Comment;
import models.Twit;
import models.User;
import org.hibernate.SessionFactory;
import repositories.SessionFactorySingleton;
import services.CommentService;
import services.TwitService;
import services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Menu {
    SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    Scanner scanner = new Scanner(System.in);
    TwitService twitService = new TwitService();
    UserService userService = new UserService();
    CommentService commentService = new CommentService();

    public void firstMenu() {
        while (true) {
            System.out.println("Welcome \n" +
                    "1. SignUp \n" +
                    "2. LogIn \n" +
                    "3. Search account by user name \n" +
                    "4. Exit");
            Integer input = scanner.nextInt();
            switch (input) {
                case 1:
                    signUp();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    searchByUserName();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter number between 1 upto 3");
            }
        }
    }

    public void searchByUserName() {
        System.out.println("Enter your user name : ");
        String userName = scanner.next();
        if (userService.findByUserName(userName) != null) {
            System.out.println(userService.findByUserName(userName).toString() + "\n");
            firstMenu();
        }
    }

    public void signUp() {
        while (true) {
            System.out.println("Please choose your username: ");
            String username = scanner.next();
            if (userService.findByUserName(username) != null) {
                System.out.println("This username exists please choose another username");
            } else {
                System.out.println("Password: ");
                String password = scanner.next();
                System.out.println("First name: ");
                String firstName = scanner.next();
                System.out.println("Last name: ");
                String lastName = scanner.next();
                System.out.println("National code: ");
                String nationalCode = scanner.next();
                System.out.println("Phone number: ");
                String phoneNumber = scanner.next();
                User user = new User(username, password, firstName, lastName,nationalCode,phoneNumber,new HashSet<>(),new HashSet<>());
                User returnedUser = userService.save(user);
                System.out.println("User id : " + returnedUser.getId());
                firstMenu();
            }
        }
    }

    public void logIn() {
        String username, password;
        do {
            System.out.println("Enter your username: ");
            username = scanner.next();
            System.out.println("Enter your password: ");
            password = scanner.next();
            if ((userService.findByUserName(username) != null) &&
                    (userService.findByUserName(username).getPassword().equals(password))) {
                User userId = userService.findByUserName(username);
                userMenu(userId);
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public void userMenu(User user) {
        while(true) {
            System.out.println("1. Add new account \n" +
                    "2. See your account \n" +
                    "3. Delete your account\n" +
                    "4. Edit your account\n" +
                    "5. Twit part\n" +
                    "6. Comment part\n" +
                    "7. See all twits\n" +
                    "8. Back");
            Integer customerChoice = scanner.nextInt();
            switch (customerChoice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    seeAccount(user);
                    break;
                case 3:
                    deleteAccount(user);
                    break;
                case 4:
                    editAccount(user);
                    break;
                case 5:
                    twitPart(user);
                    break;
                case 6:
                    commentPart(user);
                    break;
                case 7:
                    seeAllTwits(user);
                    break;
                case 8:
                    firstMenu();
                default:
                    System.out.println("Please enter a number between 1 upto 7");
            }
        }
    }

    public void seeAllTwits(User user) {
        System.out.println(twitService.findAll().toString());
        userMenu(user);
    }

    public void addAccount() {
        System.out.println("Add your new account");
        signUp();
    }

    public void seeAccount(User user) {
        if (userService.findAll() != null) {
            System.out.println(userService.findByUserName(user.getUserName()).toString());
            userMenu(user);
        } else {
            System.out.println("You dont have any account here");
            userMenu(user);
        }
    }


    public void deleteAccount(User user) {
        userService.delete(user);
        System.out.println("Account deleted successfully");
    }

    public void editAccount(User user) {
        while (true) {
            System.out.println("Please edit your username: ");
            String username = scanner.next();
            if (userService.findByUserName(username) != null) {
                System.out.println("This username exists, please enter correct another username");
                editAccount(user);
            } else {
                System.out.println("Edit password: ");
                String password = scanner.next();
                System.out.println("Edit first name: ");
                String firstName = scanner.next();
                System.out.println("Edit last name: ");
                String lastName = scanner.next();
                System.out.println("Edit national code: ");
                String nationalCode = scanner.next();
                System.out.println("Edit phone number: ");
                String phoneNumber = scanner.next();
                User user1 = new User(user.getId(), username, password, firstName, lastName,nationalCode,phoneNumber,new HashSet<>(),new HashSet<>());
                userService.update(user1);
                System.out.println("Account updated");
                userMenu(user);
            }
        }
    }

    public void twitPart(User user) {
        while (true) {
            System.out.println("1. Add new twit \n" +
                    "2. See your twits \n" +
                    "3. See twit comments\n" +
                    "4. Delete your twits\n" +
                    "5. Edit your twits\n" +
                    "6. Back");
            Integer customerChoice = scanner.nextInt();
            scanner.nextLine();
            switch (customerChoice) {
                case 1:
                    addNewTwit(user);
                    break;
                case 2:
                    seeTwits(user);
                    break;
                case 3:
                    seeSpecialTwits(user);
                    break;
                case 4:
                    deleteTwits(user);
                    break;
                case 5:
                    editTwits(user);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("You must enter 1,2,3,4 or 5");
            }
        }
    }

    public void addNewTwit(User user) {
        System.out.println("Add your new twit (You can use 280 character)");
        String newTwit = scanner.nextLine();
        Twit twit = new Twit(newTwit, user,new HashSet<>());
        Twit returnedTwit = twitService.save(twit);
        System.out.println("Twit id : " + returnedTwit.getId());
        twitPart(user);
    }

    public void seeTwits(User user) {
        if (twitService.findUserTwits(user.getId()) != null) {
            List<Twit> twits = twitService.findUserTwits(user.getId());
            for (Twit t:twits) {
                System.out.println(t.toString());
            }
        } else {
            System.out.println("You dont have any twit here");
            twitPart(user);
        }
    }

    public void deleteTwits(User user) {
        seeTwits(user);
        System.out.println("Enter your twit id : ");
        Long twitId = scanner.nextLong();
        Twit twit = new Twit(twitId);
        twitService.delete(twit);
        System.out.println("Twit number " + twitId + " deleted successfully");
        twitPart(user);
    }

    public void editTwits(User user) {
        while (true) {
            seeTwits(user);
            System.out.println("Twit id : ");
            Long id = scanner.nextLong();
            System.out.println("Edit twit: ");
            String twitText = scanner.next();
            Twit twit = new Twit(id, twitText);
            if (twitService.findById(twit.getId()) == null) {
                System.out.println("This twit does not exist, please go to add part to adding new twit");
                twitPart(user);
            } else {
                Twit twit1 = new Twit(user.getId(), twitText);
                twitService.update(twit1);
                System.out.println("Edit was successful");
                twitPart(user);
            }
        }
    }

    public void commentPart(User user) {
        System.out.println("1. Leave a new comment \n" +
                "2. See your comments \n" +
                "3. Delete your comments\n" +
                "4. Edit your comments\n" +
                "5. Back");
        Integer customerChoice = scanner.nextInt();
        scanner.nextLine();
        switch (customerChoice) {
            case 1:
                LeaveNewComment(user);
                break;
            case 2:
                seeComments(user);
                break;
            case 3:
                deleteComments(user);
                break;
            case 4:
                editComments(user);
                break;
            case 5:
                userMenu(user);
                break;
            default:
                System.out.println("You must enter 1,2,3,4 or 5");
        }
    }

    public void LeaveNewComment(User user) {
        System.out.println(twitService.findAll().toString());
        System.out.println("Choose twit id : ");
        Long twitId = scanner.nextLong();
        scanner.nextLine();
        try {
            Twit twit1 = twitService.findById(twitId);
            System.out.println("Comment : ");
            String comment = scanner.nextLine();
            Comment comment1 = new Comment(comment, twit1, user);
            Comment returnedComment = commentService.save(comment1);
            System.out.println( user.getUserName() + " added comment." + "\nComment id : " + returnedComment.getId());
            commentPart(user);
        }
        catch (TwitNotFoundException t){
            System.out.println(t.getMessage());
        }
    }

    public void seeComments(User user) {
        seeTwits(user);
        System.out.println("Twit id : ");
        Long twitId = scanner.nextLong();
        scanner.nextLine();
        if (commentService.findByTwitId(twitId) != null) {
            List<Comment> comments = commentService.findByTwitId(twitId);
            for (Comment c:comments) {
                System.out.println(c);
            }
            commentPart(user);
        } else {
            System.out.println("------------------------------\nYou dont have any comment here\n------------------------------");
            commentPart(user);
        }
    }

    public void watchList(User user){
        seeTwits(user);
        System.out.println("Twit id : ");
        Long twitId = scanner.nextLong();
        scanner.nextLine();
        if (commentService.findByTwitId(twitId) != null) {
            List<Comment> comments = commentService.findByTwitId(twitId);
            for (Comment c:comments) {
                System.out.println(c);
            }
        } else {
            System.out.println("------------------------------\nYou dont have any comment here\n------------------------------");
            commentPart(user);
        }
    }

    public void deleteComments(User user) {
        watchList(user);
        System.out.println("Comment id : ");
        Long commentId = scanner.nextLong();
        Comment comment = new Comment(commentId);
        commentService.delete(comment);
        System.out.println("----------------------------------------\nComment number " + commentId + " deleted successfully\n----------------------------------------");
        commentPart(user);
    }

    public void editComments(User user) {
        while (true) {
            watchList(user);
            System.out.println("Comment id : ");
            Long commentId = scanner.nextLong();
            System.out.println("Edit comment: ");
            String commentContent = scanner.next();
            if (commentService.findById(commentId) != null) {
                Comment returnedComment = commentService.findById(commentId);
                Comment comment = new Comment(commentId,commentContent,returnedComment.getTwit(),returnedComment.getUser());
                commentService.update(comment);
                System.out.println("--------------------\nEdit was successful\n--------------------");
                commentPart(user);
            } else {
                System.out.println("------------------------------\nThis twit does not exist!!!\n------------------------------");
                twitPart(user);
            }
        }
    }

    public void seeSpecialTwits(User user){
        seeTwits(user);
        System.out.println("Enter your twit id : ");
        Long id = scanner.nextLong();
        if(twitService.findById(id) != null){
            List<Comment> comments = commentService.findAllByTwitId(id);
            for (Comment c:comments) {
                System.out.println(c.toString());
            }
        }
    }
}

