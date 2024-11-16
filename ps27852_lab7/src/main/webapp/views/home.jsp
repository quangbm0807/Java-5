<style>
    .carousel-inner img {
        height: 500px; /* Thay đổi chiều cao theo ý muốn */
    }

    .carousel-indicators button img {
        height: 50px; /* Chiều cao cho các hình ảnh thumbnail */
    }
</style>

<!-- Carousel wrapper -->
<div class="text-center mb-4">
    <h2>Laptop</h2>
</div>
<div id="carouselExampleIndicators" data-mdb-carousel-init class="carousel slide carousel-fade" data-mdb-ride="carousel">
    <!-- Slides -->
    <div class="carousel-inner mb-5">
        <div class="carousel-item active">
            <img src="https://cdn.tgdd.vn/Files/2019/08/22/1190454/laptop-vien-man-hinh-mong-co-nhung-uu-va-nhuoc-diem-gi--4.jpg" class="d-block w-100" alt="Laptop 1" />
        </div>
        <div class="carousel-item">
            <img src="https://cdn.tgdd.vn/Files/2020/12/26/1316150/thumb_800x501.jpg" class="d-block w-100" alt="Laptop 2" />
        </div>
        <div class="carousel-item">
            <img src="https://laptop88.vn/media/news/2108_man-hinh-laptop-lenovo.jpg" class="d-block w-100" alt="Laptop 3" />
        </div>
    </div>
    <!-- Slides -->

    <!-- Controls -->
    <button data-mdb-button-init class="carousel-control-prev" type="button" data-mdb-target="#carouselExampleIndicators"
        data-mdb-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button data-mdb-button-init class="carousel-control-next" type="button" data-mdb-target="#carouselExampleIndicators"
        data-mdb-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
    <!-- Controls -->

    <!-- Thumbnails -->
    <div class="carousel-indicators" style="margin-bottom: -20px;">
        <button type="button" data-mdb-button-init data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="0" class="active"
            aria-current="true" aria-label="Slide 1" style="width: 100px;">
            <img class="d-block w-100" src="https://cdn.tgdd.vn/Files/2019/08/22/1190454/laptop-vien-man-hinh-mong-co-nhung-uu-va-nhuoc-diem-gi--4.jpg" class="img-fluid" />
        </button>
        <button type="button" data-mdb-button-init data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="1"
            aria-label="Slide 2" style="width: 100px;">
            <img class="d-block w-100" src="https://cdn.tgdd.vn/Files/2020/12/26/1316150/thumb_800x501.jpg" class="img-fluid" />
        </button>
        <button type="button" data-mdb-button-init data-mdb-target="#carouselExampleIndicators" data-mdb-slide-to="2"
            aria-label="Slide 3" style="width: 100px;">
            <img class="d-block w-100" src="https://laptop88.vn/media/news/2108_man-hinh-laptop-lenovo.jpg" class="img-fluid" />
        </button>
    </div>
    <!-- Thumbnails -->
</div>
<!-- Carousel wrapper -->

<!-- JavaScript để kích hoạt carousel -->
<script>
    // Lắng nghe sự kiện click trên các button thumbnail
    const buttons = document.querySelectorAll('.carousel-indicators button');

    buttons.forEach((button, index) => {
        button.addEventListener('click', () => {
            // Khi button được nhấn, chuyển đến slide tương ứng
            const carousel = document.querySelector('#carouselExampleIndicators');
            const carouselInstance = bootstrap.Carousel.getInstance(carousel); // Lấy instance của carousel
            carouselInstance.to(index); // Chuyển đến slide với chỉ số index
        });
    });
</script>
