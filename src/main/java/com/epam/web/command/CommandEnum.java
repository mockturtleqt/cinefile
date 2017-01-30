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

    CREATE_REVIEW {
        {
            this.command = new CreateReviewCommand();
        }
    },

    CREATE_MEDIA_PERSON {
        {
            this.command = new CreateMediaPersonCommand();
        }
    },

    CREATE_MOVIE {
        {
            this.command = new CreateMovieCommand();
        }
    },

    UPDATE_MEDIA_PERSON {
        {
            this.command = new UpdateMediaPersonCommand();
        }
    },

    UPDATE_MOVIE {
        {
            this.command = new UpdateMovieCommand();
        }
    },

    UPDATE_USER {
        {
            this.command = new UpdateUserCommand();
        }
    },

    UPDATE_REVIEW {
        {
            this.command = new UpdateReviewCommand();
        }
    },

    SHOW_EDIT_MOVIE_FORM {
        {
            this.command = new ShowEditMovieFormCommand();
        }
    },

    SHOW_EDIT_USER_FORM {
        {
            this.command = new ShowEditUserFormCommand();
        }
    },

    SHOW_EDIT_MEDIA_PERSON_FORM {
        {
            this.command = new ShowEditMediaPersonFormCommand();
        }
    },

    FIND_ALL_MOVIES {
        {
            this.command = new FindAllMoviesCommand();
        }
    },

    FIND_ALL_MEDIA_PEOPLE {
        {
            this.command = new FindAllMediaPeopleCommand();
        }
    },

    UPDATE_MOVIE_RATING {
        {
            this.command = new UpdateMovieRatingCommand();
        }
    },

    CREATE_MOVIE_RATING {
        {
            this.command = new CreateMovieRatingCommand();
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
