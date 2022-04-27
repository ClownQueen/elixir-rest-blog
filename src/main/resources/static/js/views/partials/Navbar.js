import {isLoggedIn} from "../../auth.js";

export default function Navbar(props) {
    const loggedIn = isLoggedIn();

    // everyone can see home
    let html = `
        <nav>
            <a href="/" data-link>Home</a> &nbsp;`;

    // only logged in can see posts
    if(loggedIn) {
        html = html + `<a href="/posts" data-link>Posts</a> &nbsp;`;
    }

    // everyone can see about
    html = html + `<a href="/about" data-link>About</a> &nbsp;`;

    // only logged in can see user info and logout
    if(loggedIn) {
        html = html + `<a href="/users" data-link>User Info</a> &nbsp;
            <a href="/logout" data-link>Logout</a>`;
    } else {
        // if not logged in, can see login and register
        html = html + `<a href="/login" data-link>Login</a> &nbsp;
        <a href="/register" data-link>Register</a> &nbsp;`;
    }

    html = html + `</nav>`;
    return html;
}