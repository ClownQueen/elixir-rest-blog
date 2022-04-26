import {isLoggedIn} from "../../auth.js";

export default function Navbar(props) {
    const loggedIn = isLoggedIn();

    //let everyone see home
    let html =`
        <nav>
            <a href="/" data-link>Home</a>`;

            //only logged in can see posts
        if(loggedIn){
            html = html + `<a href="/posts" data-link>Posts</a>`;
        }

        //everyone can see about
        html = html + `<a href="/about" data-link>About</a>`;

        //only logged in can see user info and logout
        if(loggedIn){
            html = html + `<a href="/user" data-link>Users</a>
            <a href="/logout" data-link>Logout</a>`;
        } else {
            html = html + `<a href="/login" data-link>Login</a> 
            <a href="/register" data-link>Register</a>`
        }
        html = html + `</nav>`;
        return html;
    }