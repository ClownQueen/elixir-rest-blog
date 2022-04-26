import createView from "../createView.js";
import {getHeaders} from "../auth";

const BASE_URI = "http://localhost:8081/api/users";

export default function User(props) {
    console.log("The frontend did it. HER FAULT");
    return `
        <header>
            <h1>User Page</h1>
        </header>
        <main>
            <div class="container-fluid">
                <div class="user-data-container">
                    <p class="username" data-id="${props.user.id}">Username: ${props.user.username}</p>
                    <p class= "email" data-id="${props.user.id} ">Email: ${props.user.email}</p>               
                    </div>
                <div class="edit-user-container">
                    <form>
                        <div class="form-group">
                            <label for="new-user-password">New Password</label>
                            <input type="password" class="form-control" id="new-user-password" placeholder="Enter New Password.">
                        </div>
                        <button type="submit" class="btn btn-primary" id="edit-password-btn">Submit</button>
                    </form>
                    <div>
                        ${props.user.posts.map(post =>
                            `<h3 id="title-${post.id}">${post.title}</h3>
                             <p id="content-${post.id}">${post.content}</p>`).join('')}
                    </div>
                </div> 
            </div>     
        </main>
    `;
}

    export function UserEvents(){
     $("#edit-password-btn").click(function (){
         // 1. grab data from form fields
         const userId = 1; // $("#add-post-id").val();
         let uriExtra = '/1/updatePassword';
         // const oldPassword = $("#old-password").val()
         const newPassword = $("#new-user-password").val()

         // 2. assemble the request
         const request = {
             headers: getHeaders(),
             method: "PUT"
         }

         // 3. do the fetch with the correct URI please (check against Postman)
         fetch(`${BASE_URI}${uriExtra}?newPassword=${newPassword}`, request)
             .then(res => {
                 console.log(`${request.method} SUCCESS: ${res.status}`);
             }).catch(error => {
             console.log(`${request.method} ERROR: ${error}`);
         }).finally(() => {
             createView("/users");
         });

     });
    }