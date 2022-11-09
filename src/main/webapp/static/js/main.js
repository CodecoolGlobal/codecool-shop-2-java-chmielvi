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
    console.log(products)
    drawProducts(products)
}

function drawProducts(ProductsList) {
    const productsContainer = document.querySelector("#products")
    productsContainer.innerHTML = ""
    ProductsList.forEach(product => {
        console.log(product)
        let container = document.createElement('div');
        container.classList.add('col', 'col-sm-12', 'col-md-6', 'col-lg-4')
        container.innerHTML = getCard(product)
        productsContainer.appendChild(container)
    })
}

function getCard(product){
    return `<div class="card">
                <img class="" src="/static/img/${product.image}.png" alt="" width="348" height="400" />
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <hr>
                    <p class="card-main-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" href="#">Add to cart</a>
                    </div>
                </div>
            </div>`;



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
}}

