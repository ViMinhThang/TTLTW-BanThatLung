
$(document).ready(function () {
    $('input[name="paymentMethod"]').change(function () {
        console.log(1)
        const selectedMethod = $(this).val();
        $('.submitPaymentMethod').val(selectedMethod);
        $('#openFormButtonPayment').text('Thanh toán với ' + selectedMethod);
    });
});

$(document).ready(function () {
    $('select[name="selectedAddress"]').change(function () {
        const selectedAddress = $(this).val();
        $('.submitDeliveryAddress').val(selectedAddress);
    });
});

$(document).ready(function () {
    const provinceName = $("#province strong").text().trim();
    const districtName = $("#district strong").text().trim();
    const wardName = $("#ward strong").text().trim();

    $.getJSON('/location?action=province', function (provinceData) {
        const province = provinceData.data.find(p => p.ProvinceName === provinceName);
        if (!province) return console.error('Không tìm thấy tỉnh:', provinceName);

        $.getJSON('/location?action=district&province_id=' + province.ProvinceID, function (districtData) {
            const district = districtData.data.find(d => d.DistrictName === districtName);
            if (!district) return console.error('Không tìm thấy quận/huyện:', districtName);

            $.getJSON('/location?action=ward&district_id=' + district.DistrictID, function (wardData) {
                const ward = wardData.data.find(w => w.WardName === wardName || w.WardCode === wardName);
                if (!ward) return console.error('Không tìm thấy phường/xã:', wardName);

                // Gọi API tính phí
                calculateShipping(district.DistrictID, ward.WardCode);
            });
        });
    });

    function calculateShipping(districtId, wardId) {
        $.ajax({
            url: '/shipping-fee',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                to_district_id: districtId,
                to_ward_code: wardId
            }),
            success: function (response) {
                const fee = response.data.total;
                $('.shipmentPrice').text(fee.toLocaleString("vi-VN") + ' VNĐ');
                const productTotal = parseInt('${totalPrice}');
                const grandTotal = productTotal + fee;
                $('.grandTotal').text(grandTotal.toLocaleString("vi-VN") + ' VNĐ');
            }
        });
    }
});
