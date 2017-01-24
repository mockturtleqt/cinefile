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

    SHOW_MEDIA_PERSON_PAGE {
        {
            this.command = new ShowMediaPersonPageCommand();
        }
    },

    SHOW_USER_PAGE {
        {
            this.command = new ShowUserPageCommand();
        }
    },

    FIND_MOVIES_BY_NAME {
        {
            this.command = new FindMovieCommand();
        }
    },

    ADD_REVIEW {
        {
            this.command = new AddReviewCommand();
        }
    },

    UPDATE_REVIEW {
        {
            this.command = new UpdateReviewCommand();
        }
    },

    DELETE_REVIEW {
        {
            this.command = new DeleteReviewCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
