import createView from "../createView.js";
import {getHeaders} from "../auth.js";
// import addLoginEvent from "../auth";

const POST_URI = "http://localhost:8081/api/posts";

export default function PostIndex(props) {
    console.log(props)
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
                 ${post.categories.map(category => {
                    return`
                    <p id="categories-${category.id}">${category.name}</p>`
                }).join('')}
                  <button id="edit-button-${post.id}" type="button" class="btn btn-success mb-3 edit-button" data-id="${post.id}">Edit</button>
                 <button id="delete-button-${post.id}" type="button" class="btn btn-danger mb-3 delete-button" data-id="${post.id}">Delete</button>`
                
    }).join('')} 
            </div>
            
            <div class="container" id="add-posts">
               <form id="add-posts-form">
                <div class="mb-3">
                    <input type="text" class="from-control" id="add-posts-id" disabled placeholder="0">
                </div>
                <div class="mb-3">
                    <label for="add-posts-title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="add-posts-title" placeholder="Enter Title">
                </div>
                <div class="mb-3">
                    <label for="add-posts-content" class="form-label">Content</label>
                    <input type="text" class="form-control" id="add-posts-content" placeholder="Enter Content">
                </div>
                
                <!-- display array of categories as checkboxes-->
                ${props.categories.map(category => {
        return`
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="category-${category.id}" value="${category.id}">
                    <label class="form-check-label" for="category-${category.id}">${category.name}</label>
                </div>`
    }).join('')}
                <button id="add-posts-button" class="btn btn-primary mb-3">Add Post</button>
                <button id="update-posts-button" class="btn btn-secondary mb-3">Save Post</button>
               </form>
           </div>
        </main>
    </div>
    `;
}

export function PostsEvent() {
    createAddPostListener();
    //TODO: add edit post listener function
    editListener();
    //TODO: add delete post listener function
    deleteListener();
}

function createAddPostListener() {
    console.log("adding add post listener")
    $("#add-posts-button").click(function () {
        const newPost = {
            title: $("#add-posts-title").val(),
            content: $("#add-posts-content").val(),
            // categories:
        }

        const request = {
            method: "Post",
            headers: getHeaders(),
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts");
        });
    });
}

function editListener(){
    $(".edit-button").click(function (){
        const postId = $(this).data("id");

        let titleId = "title-" + postId;
        const updatedTitle = $(`#${titleId}`).text();
        console.log(updatedTitle);

        let contentId = "content-" + postId;
        const updatedContent = $(`#${contentId}`).text();

        $("#add-posts-id").val(postId);
        $("#add-posts-title").val(updatedTitle);
        $("#add-posts-content").val(updatedContent);
        $("#update-posts-button").data("id", postId)
    });

    $("#update-posts-button").click(function (){
        const newTitle = $("#add-posts-title").val();
        const newContent = $("#add-posts-content").val();
        const postId = $(this).data("id");

        const updatedPost = {
            id: postId,
            title: newTitle,
            content: newContent
        }

        console.log("Ready to update:")
        console.log(updatedPost)

        const request = {
            method: "PUT",
            headers: getHeaders(),
            body: JSON.stringify(updatedPost)
        }
        fetch(`${POST_URI}/${postId}`, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts");
        });
    });
}

function deleteListener(){
    $(".delete-button").click(function (){
        const postId = $(this).data("id");
        console.log("deleting Post: " + postId);

        const request = {
            method: "DELETE",
            headers: getHeaders()
        };
        fetch(`${POST_URI}/${postId}`, request)
            .then(res => {
                console.log("DELETE SUCCESS: " + res.status);
            }).catch(error => {
            console.log("DELETE ERROR: " + error)
        }).finally(() => {
            createView("/posts")
        });
    });
}