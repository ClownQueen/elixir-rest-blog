import createView from "../createView.js";
import addLoginEvent from "../auth";

const POST_URI = "http://localhost:8081/api/posts";

export default function PostIndex(props) {
    return `
    <div class="container-fluid">
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => {
                return `<h3 id="title-${post.id}">${post.title}</h3>
                 <p id="content-${post.id}">${post.content}</p>
                 <a href="#" class="edit-link" data-id="${post.id}">Edit</a>
                 <a href="#" class="delete-link" data-id="${post.id}">Delete</a>`
                
    }).join('')} 
            </div>
            
            <div class="container" id="add-posts">
               <form id="add-posts-form">
                <div class="mb-3">
                    <label for="add-posts-title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="add-posts-title" placeholder="Enter Title">
                </div>
                <div class="mb-3">
                    <label for="add-posts-content" class="form-label">Content</label>
                    <input type="text" class="form-control" id="add-posts-content" placeholder="Enter Content">
                </div>
                <button id="add-posts-button" type="button" class="btn btn-primary mb-3">Save Post</button>
               </form>
           </div>
        </main>
    </div>
    `;
}

export function PostsEvent() {
    createAddPostListener();
    //TODO: add edit post listener function
    EditListener();
    //TODO: add delete post listener function
    deleteListener();
}

function createAddPostListener() {
    console.log("adding add post listener")
    $("#add-post-button").click(function (){
        const title = $("#add-post-title").val();
        const content = $("#add-post-content").val();
        const newPost = {
            title,
            content
        }
        console.log("Ready to add: ");
        console.log(newPost);

        const request = {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI, request)
            .then( res => {
                console.log(res.status)
            }).catch(error => {
            console.log(error)
        }).finally(() => {
            createView("/posts")
        });
    });
}

function EditListener(){
    $(".edit-link").click(function (){
        const postId = $(this).data("id");
        const postTitle = $("#title-" + postId).val();
        const postContent = $("#content-" + postId).val();
        console.log(postId);
        $("#add-post-title").val(postTitle);
        $("#add-post-content").val(postContent);
    });
}

function deleteListener(){
    $(".delete-link").click(function (){
        const postId = $(this).data("id")

    })
}