package com.lec.petshop.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.DogDto;
import com.lec.petshop.dto.FreeBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("FreeBoardUpFolder");
		int maxSize = 1024*1024*10;
		String[] ffilename = {"","",""};
		String[] originalFfilename = {"","",""};
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement(); // 파라미터 이름 받아오기
				ffilename[idx] = mRequest.getFilesystemName(param); // 그 파라미터 이름으로 저장된 파일을 가지고 옴
				originalFfilename[idx] = mRequest.getOriginalFileName(param);// 해당 파라미터 이름으로 첨부된 오리지널 파일 이름 가지고 옴
				idx++;
			}
			String pageNum = mRequest.getParameter("pageNum");
			int fnum = Integer.parseInt(mRequest.getParameter("fnum"));
			String ftitle = mRequest.getParameter("ftitle");
			String fcontent = mRequest.getParameter("fcontent");
			String fip = request.getRemoteAddr();
			FreeBoardDao dao = FreeBoardDao.getInstance();
			FreeBoardDto dto = dao.modifyView_replyView(fnum); 
			if(ffilename[0] == null) {
				ffilename[0] = dto.getFfilename1();
			}
			if(ffilename[1] == null) {
				ffilename[1] = dto.getFfilename2();
			}
			if(ffilename[2] == null ) {
				ffilename[2] = dto.getFfilename3();
			}
			int result = dao.modifyBoard(ftitle, fcontent, fip, ffilename[0], ffilename[1], ffilename[2], fnum);
			if(result == FreeBoardDao.SUCCESS) {
				request.setAttribute("modifyResult", result);  // 성공하면 1
			} else {
				request.setAttribute("modifyResult", result);  // 실패하면 0
			}
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("fnum", fnum);				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (String f : ffilename) {
			if (f != null) {
				// 파일복사
				InputStream is = null;
				OutputStream os = null;
				try {
					File serverfile = new File(path + "/" + f); 
					if (serverfile.exists()) { 
						is = new FileInputStream(serverfile); 
						os = new FileOutputStream( "C:\\choijinyoung\\source\\petshop_project\\petshop\\WebContent\\FreeBoardUpFolder/" + f);// 복사될 파일
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
