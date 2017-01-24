$(document).ready(function () {
    var editBtn = $(".edit-btn");
    var saveBtn = $(".save-btn");
    var reviewTitle = editBtn.nextAll(".review-title").eq(0);
    var reviewBody = editBtn.nextAll(".review-body").eq(0);
    var titleInput = $("<input name='review-title-input' class='review-title-input'/>");
    var bodyInput = $("<input name='review-body-input' class='review-body-input'/>");
    var editReviewForm = $(".edit-review-form");

    editBtn.click(function () {
        titleInput.val(reviewTitle.text());
        bodyInput.val(reviewBody.text());

        editReviewForm.append(titleInput);
        editReviewForm.append(bodyInput);

        reviewTitle.hide();
        reviewBody.hide();

        editBtn.hide();
        saveBtn.show();
    });
});
