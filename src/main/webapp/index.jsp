<%--<%@page contentType="text/html" pageEncoding="utf-8" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Index</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<c:set var="locale" value="${pageContext.request.locale}"/>&ndash;%&gt;--%>
<%--<fmt:setLocale value="${locale}"/>--%>
<%--<form action="controller">--%>
    <%--<input type="hidden" name="command" value="change_language">--%>
    <%--<select name="language">--%>
        <%--<option value="en_US">English</option>--%>
        <%--<option value="ru_RU">Русский</option>--%>
    <%--</select>--%>
    <%--<button type="submit"><fmt:message key="welcome"/></button>--%>
<%--</form>--%>

<%--&lt;%&ndash;Preferred locale: ${locale.displayName}&ndash;%&gt;--%>
<%--&lt;%&ndash;<br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;Country: ${locale.displayCountry}&ndash;%&gt;--%>
<%--&lt;%&ndash;<br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;Language: ${locale.displayLanguage}&ndash;%&gt;--%>
<%--&lt;%&ndash;<hr/>&ndash;%&gt;--%>

<%--<br/>--%>
<%--<a href="jsp/login.jsp">login</a>--%>
<%--&lt;%&ndash;<jsp:forward page="jsp/login.jsp"/>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cinefile</title>
    <meta charset="utf-8">
    <link href="css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="home">
<fmt:setLocale value="${locale}"/>
<c:import url="jsp/header.jsp"/>

<section class="section main">
    <div class="section-title">
        <h2><fmt:message key="top.movies.msg"/></h2>
    </div>
    <section class="section-movies">
        <ol>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">The Shawshank Redemption</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/1_the_shawshank_redemption.jpg" alt="The Shawshank redemption"/>
                        </a>
                    </div>

                    <p class="description">Bank Merchant Andy Dufresne is convicted of the murder of his wife and her
                        lover, and sentenced to life imprisonment at Shawshank prison. Life seems to have taken a turn
                        for the worse, but fortunately Andy befriends some of the other inmates, in particular a
                        character known only as Red. Over time Andy finds ways to live out life with relative ease as
                        one can in a prison, leaving a message for all that while the body may be locked away in a cell,
                        the spirit can never be truly imprisoned.</p>
                </div>
            </li>
            <li>
                <div class="movie">

                    <div class="poster">
                        <a href="#">
                            <h4 class="title">The Godfather</h4>
                        </a>
                        <a href="#">
                            <img src="img/2_the_godfather.jpg" alt="The godfather"/>
                        </a>
                    </div>
                    <p class="description">The Godfather "Don" Vito Corleone is the head of the Corleone mafia family in
                        New York. He is at the event of his daughter's wedding. Micheal, Vito's youngest son and a
                        decorated WW II Marine is also present at the wedding. Micheal seems to be uninterested in being
                        a part of the family business. Vito is a powerful man, and is kind to all those who give him
                        respect but is ruthless against those who do not. But when a powerful and treacherous rival
                        wants to sell drugs and needs the Don's influence for the same, Vito refuses to do it. What
                        follows is a clash between Vito's fading old values and the new ways which may cause Micheal to
                        do the thing he was most reluctant in doing and wage a mob war against all the other mafia
                        families which could tear the Corleone family apart.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">The Godfather: Part II</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/3_the_godfather_part2.jpg" alt="The godfather 2"/>
                        </a>
                    </div>
                    <p class="description">The life of Vito Corleone is shown as he becomes from a boy born in Sicily to
                        one of the most respected mafia dons of New York while Micheal attempts to expand his business
                        empire into Las Vegas, Florida and pre-revolution Cuba while facing his own personal problems
                        trying to keep his collapsing marriage and relationship with his brother intact.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">The Dark Knight</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/4_the_dark_knight.jpg" alt="The dark knight"/>
                        </a>
                    </div>
                    <p class="description">Set within a year after the events of Batman Begins, Batman, Lieutenant James
                        Gordon, and new district attorney Harvey Dent successfully begin to round up the criminals that
                        plague Gotham City until a mysterious and sadistic criminal mastermind known only as the Joker
                        appears in Gotham, creating a new wave of chaos. Batman's struggle against the Joker becomes
                        deeply personal, forcing him to "confront everything he believes" and improve his technology to
                        stop him. A love triangle develops between Bruce Wayne, Dent and Rachel Dawes.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Schindler's List</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/5_schindlers_list.jpg" alt="Shindler's list"/>
                        </a>
                    </div>
                    <p class="description">Oskar Schindler is a vainglorious and greedy German businessman who becomes
                        an unlikely humanitarian amid the barbaric German Nazi reign when he feels compelled to turn his
                        factory into a refuge for Jews. Based on the true story of Oskar Schindler who managed to save
                        about 1100 Jews from being gassed at the Auschwitz concentration camp, it is a testament to the
                        good in all of us.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Pulp Fiction</h4>
                    </a>


                    <div class="poster">
                        <a href="#">
                            <img src="img/6_pulp_fiction.jpg" alt="Pulp fiction"/>
                        </a>
                    </div>
                    <p class="description">Jules Winnfield (Samuel L. Jackson) and Vincent Vega (John Travolta) are two
                        hit men who are out to retrieve a suitcase stolen from their employer, mob boss Marsellus
                        Wallace (Ving Rhames). Wallace has also asked Vincent to take his wife Mia (Uma Thurman) out a
                        few days later when Wallace himself will be out of town. Butch Coolidge (Bruce Willis) is an
                        aging boxer who is paid by Wallace to lose his next fight. The lives of these seemingly
                        unrelated people are woven together comprising of a series of funny, bizarre and uncalled-for
                        incidents.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Fight Club</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/7_fight_club.jpg" alt="Fight club"/>
                        </a>
                    </div>
                    <p class="description">A young man leads a pretty humdrum life assessing car crashes to determine if
                        his automobile company should issue recalls to fix problems. He also suffers from insomnia and
                        takes to attending group therapy sessions for people who have survived various diseases. There
                        he meets Marla who like him attends these sessions though she is neither a victim nor a
                        survivor. His life changes when he meets Tyler Durden on a flight home. Tyler seems to be
                        everything that he's not and together they create a men-only group for bare knuckle fighting. It
                        soon becomes all the rage with fight clubs springing up across the country and the group itself
                        becoming an anti-capitalist domestic terrorist organization. Tyler and Maria develop a
                        relationship leaving him often on the outside of what is going on. He soon finds that the group
                        is out of control and after a major self-revelation decides there is only one way out.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Forrest Gump</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/8_forrest_gump.jpg" alt="Forrest Gump"/>
                        </a>
                    </div>
                    <p class="description">Forrest Gump is a simple man with a low I.Q. but good intentions. He is
                        running through childhood with his best and only friend Jenny. His 'mama' teaches him the ways
                        of life and leaves him to choose his destiny. Forrest joins the army for service in Vietnam,
                        finding new friends called Dan and Bubba, he wins medals, creates a famous shrimp fishing fleet,
                        inspires people to jog, starts a ping-pong craze, creates the smiley, writes bumper stickers and
                        songs, donates to people and meets the president several times. However, this is all irrelevant
                        to Forrest who can only think of his childhood sweetheart Jenny Curran, who has messed up her
                        life. Although in the end all he wants to prove is that anyone can love anyone.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Inception</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/9_inception.jpg" alt="Inception"/> </a>
                    </div>
                    <p class="description">Dom Cobb is a skilled thief, the absolute best in the dangerous art of
                        extraction, stealing valuable secrets from deep within the subconscious during the dream state,
                        when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in
                        this treacherous new world of corporate espionage, but it has also made him an international
                        fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at
                        redemption. One last job could give him his life back but only if he can accomplish the
                        impossible - inception. Instead of the perfect heist, Cobb and his team of specialists have to
                        pull off the reverse: their task is not to steal an idea but to plant one. If they succeed, it
                        could be the perfect crime. But no amount of careful planning or expertise can prepare the team
                        for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could
                        have seen coming.</p>
                </div>
            </li>
            <li>
                <div class="movie">
                    <a href="#">
                        <h4 class="title">Léon: The Professional</h4>
                    </a>

                    <div class="poster">
                        <a href="#">
                            <img src="img/10_leon.jpg" alt="Leon"/> </a>
                    </div>
                    <p class="description">After her father, mother, older sister and little brother are killed by her
                        father's employers, the 12-year-old daughter of an abject drug dealer is forced to take refuge
                        in the apartment of a professional hitman who at her request teaches her the methods of his job
                        so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved
                        brother.</p>
                </div>
            </li>
        </ol>
    </section>
</section>
<c:import url="jsp/footer.jsp"/>
</body>
</html>

