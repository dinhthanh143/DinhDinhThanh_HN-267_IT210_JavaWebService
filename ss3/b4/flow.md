# Bản thiết kế luồng I/O - Bài 4

## Sơ đồ luồng dữ liệu (Data Flow)

```
URL Input: /bai4/products?category=chay&limit=10
       ↓
Controller: LegacyController.getProducts()
       ↓
@RequestParam: category="chay", limit=10
       ↓
ModelMap.addAttribute():
   - key: "category" → value: "chay"
   - key: "limit" → value: 10  
   - key: "message" → value: "Tìm kiếm thành công"
       ↓
Return View: "productList"
       ↓
JSP View: /WEB-INF/views/productList.jsp
       ↓
Display: ${category}, ${limit}, ${message}
```

## Chi tiết luồng

1. **Dữ liệu vào từ URL:**
   - `category`: String (loại sản phẩm)
   - `limit`: Integer (giới hạn số lượng)

2. **Controller đẩy vào ModelMap:**
   - Key: "category" → Value: category từ URL
   - Key: "limit" → Value: limit từ URL
   - Key: "message" → Value: "Tìm kiếm thành công"

3. **File View (JSP):**
   - Tên file: `productList.jsp`
   - Đường dẫn: `/WEB-INF/views/productList.jsp`

4. **Hiển thị trên JSP:**
   - `${category}` - hiển thị loại sản phẩm
   - `${limit}` - hiển thị giới hạn
   - `${message}` - hiển thị thông báo
