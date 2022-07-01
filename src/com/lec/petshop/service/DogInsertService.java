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
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.DogDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DogInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("DogImageUpFolder");
		int maxSize = 1024 * 1024 * 10;
		String[] dimage = { "", "", "", "", "" };
		String[] originalDimage = { "", "", "", "", "" };
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement(); // 파라미터 이름 받아오기
				dimage[idx] = mRequest.getFilesystemName(param); // 그 파라미터 이름으로 저장된 파일을 가지고 옴
				originalDimage[idx] = mRequest.getOriginalFileName(param);// 해당 파라미터 이름으로 첨부된 오리지널 파일 이름 가지고 옴
				idx++;
			}
			String dname = mRequest.getParameter("dname");
			String dgender = mRequest.getParameter("dgender");
			String dbirthStr = mRequest.getParameter("dbirth");
			Date dbirth = null;
			if (!dbirthStr.equals("")) {
				dbirth = Date.valueOf(dbirthStr);
			}
			int dbreedno = Integer.parseInt(mRequest.getParameter("dbreedno"));
			int dprice = Integer.parseInt(mRequest.getParameter("dprice"));
			String dcontent = mRequest.getParameter("dcontent");
			DogDao dao = DogDao.getInstance();
			HttpSession session = request.getSession();
			String aid =  ((AdminDto) session.getAttribute("admin")).getAid();
			String dip = request.getRemoteAddr();
			DogDto dog = new DogDto(0, dname, dgender, dbirth, dprice, dbreedno, aid, dcontent, dimage[0],  dimage[1],  dimage[2],  dimage[3],  dimage[4], dip, 0, 1, null, null);
			int result = dao.insertDog(dog);
			if(result == DogDao.SUCCESS) {
				System.out.println(aid+" 관리자가 강아지 등록 성공");
				request.setAttribute("DogInsertResult", "귀여운 강아지 등록 성공");
			} else {
				System.out.println("등록 실패"+dog);
				request.setAttribute("DogInsertError", "등록 실패");
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		for (String f : dimage) {
			if (f != null) { // f가 null이 아닐때만 카피할 것
				// 파일복사
				InputStream is = null;
				OutputStream os = null;
				try {
					File serverfile = new File(path + "/" + f); // 첨부하지 않으면 filename에는 null이 들어감
					if (serverfile.exists()) { // 첨부한 파일이 있는지
						is = new FileInputStream(serverfile); // 원본 파일
						os = new FileOutputStream( "C:\\choijinyoung\\source\\petshop_project\\petshop\\WebContent\\DogImageUpFolder/" + f);// 복사될 파일
						byte[] bs = new byte[(int) serverfile.length()]; // 서버파일의 크기만큼 바이트로 읽어내겠따는 뜻, 형변환으로도int로 해주기
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
