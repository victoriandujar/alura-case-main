    const colorInput = document.getElementById("newCategory-color");
    const colorPreview = document.getElementById("colorPreview");

    colorPreview.style.backgroundColor = colorInput.value;
    colorInput.addEventListener("input", () => {
    colorPreview.style.backgroundColor = colorInput.value;
});
