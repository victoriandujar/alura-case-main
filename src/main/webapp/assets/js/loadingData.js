function showLoadingAndSubmit(form) {
    showLoading();
    setTimeout(() => form.submit(), 50);
}

function showLoading() {
    Notiflix.Loading.standard('Carregando...');
}

function hideLoading() {
    Notiflix.Loading.remove();
}

Notiflix.Loading.standard('Carregando...');

window.addEventListener('DOMContentLoaded', () => {
    Notiflix.Loading.remove();
});
