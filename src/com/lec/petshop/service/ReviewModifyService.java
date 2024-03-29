package com.lec.petshop.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dao.ReviewDao;
import com.lec.petshop.dto.MemberDto;
import com.lec.petshop.dto.ReviewDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mid = ((MemberDto)session.getAttribute("member")).getMid();
		String path = request.getRealPath("ReviewUpFolder");
		int maxSize = 1024*1024*10;
		String[] rfilename = {"","",""};
		String[] originalrfilename = {"","",""};
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement(); 
				rfilename[idx] = mRequest.getFilesystemName(param); 
				originalrfilename[idx] = mRequest.getOriginalFileName(param);
				idx++;
			}
			String pageNum = mRequest.getParameter("pageNum");
			int rnum = Integer.parseInt(mRequest.getParameter("rnum"));
			String aid = mRequest.getParameter("aid");
			String rtitle = mRequest.getParameter("rtitle");
			String rcontent = mRequest.getParameter("rcontent");  
			String rip = request.getRemoteAddr();
			ReviewDao dao = ReviewDao.getInstance();
			ReviewDto dto = dao.modifyGetDto(rnum);
			if(rfilename[0] == null) {
				rfilename[0] = dto.getRfilename1();
			}
			if(rfilename[1] == null) {
				rfilename[1] = dto.getRfilename2();
			}
			if(rfilename[2] == null ) {
				rfilename[2] = dto.getRfilename3();
			}
			if(aid == null) {
				aid = dto.getAid();
			}
			int result = dao.modifyReview(rtitle, rcontent, rip, rfilename[2], rfilename[1], rfilename[0], rnum);
			if(result == FreeBoardDao.SUCCESS) {
				request.setAttribute("modifyResult", result);  // 성공하면 1
			} else {
				request.setAttribute("modifyResult", result);  // 실패하면 0
			}		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("rnum", rnum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (String f : rfilename) {
			if (f != null) {
				InputStream is = null;
				OutputStream os = null;
				try {
					File serverfile = new File(path + "/" + f); 
					if (serverfile.exists()) { 
						is = new FileInputStream(serverfile); 
						os = new FileOutputStream( "C:\\choijinyoung\\source\\petshop_project\\petshop\\WebContent\\ReviewUpFolder/" + f);// 복사될 파일
						byte[] bs = new byte[(int) serverfile.length()]; 
						while (true) {
							int nReadCnt = is.read(bs);
							if (nReadCnt == -1) {
								break;
							}
							os.write(bs, 0, nReadCnt);
						} // while
						System.out.println("서버 첨부 & 복사 완료");
					} // if
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {

					try {
						if (os != null) {
							os.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

				}

			}
		}
	}

}
