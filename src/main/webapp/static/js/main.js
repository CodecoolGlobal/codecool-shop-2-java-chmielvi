// alert("I'm from main.js");

// let pageNumber = 1;
// let currentEndpoint = '/api/top?page=';

function main(){
    addEventListeners()
    console.log("vici")
}
main()

function addEventListeners() {
    const categories = document.querySelector("#category_buttons").children;
    console.log(categories)
    for(let i = 0; i < categories.length; i ++){
        categories[i].addEventListener("click", leadProducts)
    }
}

async function leadProducts(event){
    const categoryId = event.target.id
    const products = await getProducts(categoryId)
    // console.log(products)
}

//
// async function loadJobsPage(){
//     currentEndpoint = '/api/jobs?page=';
//     pageNumber = 1;
//     const news = await getPages(pageNumber);
//     drawCards(news)
// }
//
//
// async function loadNewestPage(){
//     currentEndpoint = '/api/newest?page=';
//     pageNumber = 1;
//     const news = await getPages(pageNumber);
//     drawCards(news)
// }
//
//
// async function refreshTopPage(){
//     const news = await getPages(pageNumber)
//     drawCards(news)
// }
//
//
function drawProducts(ProductsList) {
    const productsContainer = document.querySelector("#products")
    productsContainer.innerHTML = ""
    ProductsList.forEach(product => {
        console.log(product)
        let column = document.createElement('div');
        column.classList.add("col col-sm-12 col-md-6 col-lg-4")
        column.innerHTML = getCard(product)
        productsContainer.appendChild(column)
    })
}

function getCard(product){
    // <div className="col col-sm-12 col-md-6 col-lg-4" th:each="product,iterStat : ${products}">
    // <img src="img_girl.jpg" alt="Girl in a jacket" width="500" height="600">
    return `<div class="card">
                <img class="" src="http://placehold.it/400x250/000/fff" th:attr="src='/static/img/' + ${product.image} + '.png'" alt="" width="348" height="400" />
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <hr>
                    <p class="card-main-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" href="#">Add to cart</a>
                    </div>
                </div>
            </div>`;

    // `<div class="col-sm-4 top-buffer">
    //             <div class="card bg-dark text-white" style="width: 18rem;">
    //                 <div class="card-body">
    //                     <h6 class="card-subtitle "><a href="${news.url}" >${news.title}</a></h6>
    //                     <p class="card-text">${news.time_ago}</p>
    //                     <p class="card-text">${news.user}</p>
    //                 </div>
    //             </div>
    //         </div>`
}
//
// async function loadPreviousPage(){
//     if(pageNumber > 1){
//         const news = await getPages(--pageNumber)
//         drawCards(news)
//
//     }
//     // const news = await getTopPage(1)
// }
//
// async function loadNextPage(){
//     if(pageNumber !== 10){
//         const news = await getPages(++pageNumber)
//         drawCards(news)
//
//     }
// }
//
//
async function getProducts(categoryId) {
    return await apiGet( `/api/category/products?category_id=${categoryId}`);
}



async function apiGet(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (response.ok) {
        return await response.json();
    }
}

//"api/category?category_id=1/products"