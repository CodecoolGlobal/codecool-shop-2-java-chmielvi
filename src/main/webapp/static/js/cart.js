//alert("I'm from main.js");

// let pageNumber = 1;
// let currentEndpoint = '/api/top?page=';

function main(){
    cartButtonsListener()
}
main()



/*function addEventListeners() {
    const categories = document.getElementById("category_buttons").children;
    for (let i = 0; i < categories.length; i++) {
        if (categories[i].id === "suppliers") {
            continue
        }
        categories[i].addEventListener("click", loadProductsByCategory)
    }
    const suppliers = document.getElementById("suppliers");
    suppliers.addEventListener('change', loadProductsBySupplier)

}*/

function cartButtonsListener() {
    const counterButtons = document.querySelectorAll(".btnCart")
    const counterValue = document.querySelectorAll(".count")
    counterButtons.forEach(counterButton => counterButton.addEventListener('click', function consoleLog(event) {
        console.log("IM WORKING", event)
        if (counterButton.classList.contains("plus")) {
            console.log("Class Plus")
            increaseQuantity(event)

        } else {
            console.log("Class Minus")
            decreaseQuantity(event)
        }
    }))
}

async function increaseQuantity(event) {

    const productId = event.currentTarget.getAttribute("product")
    console.log("PRODUCT ", productId)
    await apiPost(`/api/edit-quantity?product=${productId}&changer=plus`)
    let valueDiv = document.querySelector(`.count[product="${productId}"]`)
    let value = parseInt(valueDiv.innerHTML)
    console.log(value)
    value++
    valueDiv.innerHTML = (value).toString()
    console.log(value)


}


async function decreaseQuantity(event) {
    const productId = event.currentTarget.getAttribute("product")
    console.log("PRODUCT ", productId)
    await apiPost(`/api/edit-quantity?product=${productId}&changer=minus`)
    let valueDiv = document.querySelector(`.count[product="${productId}"]`)
    let value = parseInt(valueDiv.innerHTML)
    console.log(value)
    value--
    valueDiv.innerHTML = (value).toString()
    console.log(value)
}



/*
async function loadProductsBySupplier(event){
    const suppliers = document.getElementById("suppliers")
    const supplierId = suppliers.value
    const products = await getProducts(`/api/supplier/products?supplier_id=${supplierId}`)
    drawProducts(products)
}
*/


/*async function loadProductsByCategory(event){
    const categoryId = event.target.id
    const products = await getProducts(`/api/category/products?category_id=${categoryId}`)
    drawProducts(products)
}*/

/*function drawProducts(ProductsList) {
    const productsContainer = document.querySelector("#products")
    productsContainer.innerHTML = ""
    ProductsList.forEach(product => {
        let container = document.createElement('div');
        container.classList.add('col', 'col-sm-12', 'col-md-6', 'col-lg-4')
        container.innerHTML = getCard(product)
        productsContainer.appendChild(container)
    })
}

function getCard(product) {
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

}

async function getProducts(url) {
    return await apiGet( url);
}*/



async function apiGet(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (response.ok) {
        return await response.json();
    }
}

async function apiPost(url) {
    let response = await fetch(url, {

        method: "POST",
    });
    console.log(response)
    /*if (response.ok) {
        return ;
    }*/
}