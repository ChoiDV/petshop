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
import com.lec.petshop.dto.FreeBoardDto;
import com.lec.petshop.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mid = ((MemberDto)session.getAttribute("member")).getMid();
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
				String param = paramNames.nextElement(); 
				ffilename[idx] = mRequest.getFilesystemName(param); 
				originalFfilename[idx] = mRequest.getOriginalFileName(param);
				idx++;
			}
			String ftitle = mRequest.getParameter("ftitle");
			String fcontent = mRequest.getParameter("fcontent");
			String fip = request.getRemoteAddr();
			int fnum = Integer.parseInt(mRequest.getParameter("fnum"));
			FreeBoardDao dao = FreeBoardDao.getInstance();
			FreeBoardDto dto = dao.modifyView_replyView(fnum);
			int result = dao.reply(mid, ftitle, fcontent, fip, dto.getFgroup(), dto.getFstep(), dto.getFindent(), ffilename[2], ffilename[1], ffilename[0]);
			if(result == FreeBoardDao.SUCCESS) {
				request.setAttribute("ReplyResult", result);  // 성공하면 1
			} else {
				request.setAttribute("ReplyResult", result);  // 실패하면 0
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (String f : ffilename) {
			if (f != null) {
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
