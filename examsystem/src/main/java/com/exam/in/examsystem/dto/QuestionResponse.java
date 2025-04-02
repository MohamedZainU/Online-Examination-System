package com.exam.in.examsystem.dto;

import com.exam.in.examsystem.model.Question;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionResponse {
    private List<QuestionDTO> content;
    private Pageable pageable;
    private long totalElements;
    private int totalPages;

    public QuestionResponse(Page<Question> page) {
        this.content = page.getContent().stream().map(QuestionDTO::new).collect(Collectors.toList());
        this.pageable = new Pageable(page.getNumber(), page.getSize());
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public List<QuestionDTO> getContent() {
        return content;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public static class Pageable {
        private int pageNumber;
        private int pageSize;

        public Pageable(int pageNumber, int pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }
    }

	@Override
	public String toString() {
		return "QuestionResponse [content=" + content + ", pageable=" + pageable + ", totalElements=" + totalElements
				+ ", totalPages=" + totalPages + "]";
	}
    
    
}

