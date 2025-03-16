$(document).ready(function () {
        let dropdown = $(".search-dropdown");
        let productList = $("#list__product__row");
        dropdown.removeClass("show");

        function showDropdown(query) {
            productList.empty();

            if (query.length > 0) {
                dropdown.addClass("show");
                $.ajax({
                    url: `/search?q=${query}`,
                    method: "GET",
                    success: function (data) {
                        productList.empty();

                        if (data.length > 0) {
                            data.forEach(item => {
                                productList.append(`
                                <div class="col product__col d-flex flex-wrap" style="width: 50%">
                                    <a href="/productDetails?beltId=${item.id}" class="text-decoration-none text-dark">
                                        <div class="text-center hover--black border border-white">
                                                <img src="${item.image[0]}" class="img-fluidw-100 rounded shadow-sm" alt="${item.name}"/>
                                            <div class="mt-2 text-start ps-3 pb-3">
                                                <p class="fw-bold fs-5 mb-1">${item.price}</p>
                                                <h4 class="text-muted mb-1">${item.name}</h4>
                                                <p class="badge bg-secondary">${item.discountPercent} VNĐ</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            `);
                            });
                        }
                    },
                    error: function () {
                        dropdown.append(`<div class="dropdown-item text-danger">Lỗi khi lấy dữ liệu</div>`);
                    }
                });
            }
        }

    $(".search__input").on("input click", function () {
        showDropdown($(this).val().trim());
        $(".dimmer").addClass("active");
        $(".search_container").css("z-index", "1");
    });

        $(document).on("click", function (e) {
            if (!$(e.target).closest(".search__input, .search-dropdown").length) {
                dropdown.removeClass("show");
                $(".dimmer").removeClass("active");

            }
        });
    }
)
;