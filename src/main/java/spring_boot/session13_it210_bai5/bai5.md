Phần 1: Phân tích Kịch bản lỗi
Lỗi LazyInitializationException :
 Khi  lấy một Prescription ra, danh sách details (Chi tiết thuốc) chưa được tải ngay (do ta để FetchType.LAZY ở Bài 3). Nếu đóng Session lại rồi mới gọi prescription.getDetails() ở ngoài giao diện Thymeleaf, Hibernate sẽ không còn kết nối để lấy dữ liệu nữa và tung lỗi này.
Cachs khắc phục: Dùng HQL JOIN FETCH để lấy cả Cha lẫn Con trong 1 lần truy vấn duy nhất.