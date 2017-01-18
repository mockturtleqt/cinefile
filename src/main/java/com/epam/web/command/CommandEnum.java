package com.epam.web.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },

    SIGN_UP {
        {
            this.command = new SignUpCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },

    CHANGE_LANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },

    FIND_TOP_MOVIES {
        {
            this.command = new FindTopMoviesCommand();
        }
    },

    SHOW_MOVIE_PAGE {
        {
            this.command = new ShowMoviePageCommand();
        }
    },

    FIND_MOVIES_BY_NAME {
        {
            this.command = new FindMovieCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
